import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.aquaguard.data.config.HiveMQService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject

data class WaterData(
    val ph: Float,
    val temperature: Float,
    val tds: Int,
    val waterLevel: Float
)

class SensorViewModel(application: Application) : AndroidViewModel(application) {
    private val _storeNoFilter = MutableStateFlow<WaterData?>(null) // Datos del tanque sin filtrar
    val storeNoFilter: StateFlow<WaterData?> = _storeNoFilter

    private val _storeFilter = MutableStateFlow<WaterData?>(null) // Datos del tanque filtrado
    val storeFilter: StateFlow<WaterData?> = _storeFilter

    private val _noFilterValve = MutableStateFlow(0) // Valvula de la agua sin filtrar (valvula 1)
    val noFilterValve: StateFlow<Int?> = _noFilterValve

    private val _filterValve = MutableStateFlow(0) // Valvula de la agua sin filtrar (valvula 1)
    val filterValve: StateFlow<Int?> = _filterValve

    private val _valueState = MutableStateFlow<Boolean>(false) // No me acuerdo que hace pero mejor no moverle
    val valueState: StateFlow<Boolean> = _valueState

    private val topicPublish = "aquaguard/data/water"
    private val valvePublish = "aquaguard/data/waterValve" // Valvula 1
    private val valvePublish2 = "aquaguard/data/waterValve2" // Valvula 2
    private val mqttService = HiveMQService

    fun connectToHiveMQ() {
        mqttService.connect { topic, payload ->
            try {
                val json = JSONObject(payload)

                val idAlmacen = if (!json.isNull("idAlmacen")) json.getInt("idAlmacen") else -1
                val ph = json.optDouble("ph", Double.NaN).toFloat()
                val temperatura = json.optDouble("temperatura", Double.NaN).toFloat()
                val tds = json.optInt("tds", -1)
                val waterLevel = json.optDouble("cantidad").toFloat()

                if (!ph.isNaN() && !temperatura.isNaN() && tds != -1) {
                    val waterData = WaterData(ph, temperatura, tds, waterLevel)

                    Log.i("MQTT", "$idAlmacen $ph $temperatura $tds $waterLevel")

                    _valueState.value = false

                    when (idAlmacen) {
                        0 -> _storeNoFilter.value = waterData
                        1 -> _storeFilter.value = waterData
                    }
                } else {
                    _valueState.value = true
                }

            } catch (e: Exception) {
                Log.e("MQTT", "Error al parsear JSON: ${e.message}")
            }
        }
    }

    fun openNoFilterValve(valve: Int) {
        if (valve == 1) { // Abrir valvula
            publishMessage("1", valvePublish)
            _noFilterValve.value = 1
        } else if (valve == 0) { // Cerar valvula
            publishMessage("0", valvePublish)
            _noFilterValve.value = 0
        }
    }

    fun openFilterValve(valve: Int) {
        if (valve == 1) { // Abrir valvula
            publishMessage("1", valvePublish2)
            _filterValve.value = 1
        } else if (valve == 0) { // Cerar valvula
            publishMessage("0", valvePublish2)
            _filterValve.value = 0
        }
    }

    fun publishMessage(message: String, topic: String) {
        mqttService.publish(topic, message)
    }

    override fun onCleared() {
        super.onCleared()
        mqttService.disconnect()
    }
}
