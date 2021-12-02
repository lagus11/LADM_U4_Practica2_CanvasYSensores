package mx.tecnm.tepic.ladm_u4_practica2_canvasysensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager : SensorManager
    lateinit var lienzo :Lienzo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lienzo = Lienzo(this)
        setContentView(lienzo)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when(event.sensor.type) {
            Sensor.TYPE_LIGHT -> {
                var colorRojo = (event.values[0] * 255) / 10000
                lienzo.colorR = colorRojo.toInt()
            }
            Sensor.TYPE_ACCELEROMETER -> {
                var x = event.values[0]
                lienzo.coorX = -x*50+400
            }//tipo
        }//when
        lienzo.postInvalidate()

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}