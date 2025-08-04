package com.example.aquaguard.data.config

import android.util.Log
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import org.json.JSONObject
import java.nio.charset.StandardCharsets
import java.util.*

object HiveMQService {

    private val host = "49fb7ea36be74d81a901c07a587303cb.s1.eu.hivemq.cloud"
    private val username = "Trokers"
    private val password = "Trokers1!"
    private val port = 8883
    private val clientId = UUID.randomUUID().toString()
    private val topicFilter = "aquaguard/#"

    private val client: Mqtt5AsyncClient = Mqtt5Client.builder()
        .identifier(clientId)
        .serverHost(host)
        .serverPort(port)
        .sslWithDefaultConfig()
        .buildAsync()

    fun connect(onMessageReceived: (topic: String, message: String) -> Unit) {
        client.connectWith()
            .simpleAuth()
            .username(username)
            .password(password.toByteArray())
            .applySimpleAuth()
            .send()
            .whenComplete { connAck, throwable ->
                if (throwable != null) {
                    Log.e("MQTT", "Error al conectar: ${throwable.localizedMessage}", throwable)
                } else {
                    Log.i("MQTT", "Conectado a HiveMQ: $connAck")
                    subscribe(onMessageReceived)
                }
            }
    }

    private fun subscribe(onMessageReceived: (String, String) -> Unit) {
        client.subscribeWith()
            .topicFilter(topicFilter)
            .callback { publish: Mqtt5Publish ->
                try {
                    val topic = publish.topic.toString()
                    val payload = publish.payload.orElse(null)?.let { buffer ->
                        val copy = buffer.duplicate()
                        val bytes = ByteArray(copy.remaining())
                        copy.get(bytes)
                        String(bytes, StandardCharsets.UTF_8)
                    } ?: ""

                    Log.d("MQTT", "Mensaje recibido - Topic: $topic - Payload: $payload")
                    onMessageReceived(topic, payload)
                } catch (e: Exception) {
                    Log.e("MQTT", "Error procesando payload: ${e.message}", e)
                }
            }
            .send()
    }

    fun publish(topic: String, message: String){
        if (!client.state.isConnected) {
            Log.e("MQTT", "No se puede publicar: cliente no conectado")
            return
        }

        Log.i("HIVEMQ", "Mensaje: $message")

        client.publishWith()
            .topic(topic)
            .payload(message.toByteArray(StandardCharsets.UTF_8))
            .send()
            .whenComplete { _, throwable ->
                if (throwable != null) {
                    Log.e("MQTT", "Error al publicar mensaje: ${throwable.localizedMessage}")
                } else {
                    Log.i("MQTT", "Mensaje publicado en $topic: $message")
                }
            }
    }

    fun isConnected(): Boolean {
        if (client.state.isConnected) {
            return true
        } else {
            return false
        }
    }

    fun disconnect() {
        if (client.state.isConnected) {
            client.disconnect()
            Log.i("MQTT", "Cliente desconectado")
        }
    }
}
