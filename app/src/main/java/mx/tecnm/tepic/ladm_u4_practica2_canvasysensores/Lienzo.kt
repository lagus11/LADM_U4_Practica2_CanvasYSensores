package mx.tecnm.tepic.ladm_u4_practica2_canvasysensores

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View
import androidx.core.content.ContextCompat.getSystemService


class Lienzo(act:MainActivity): View(act) {
    var colorR = 0
    var coorX = 400f
    var coorY = 400f
    val principal = act
    var movimientoParacaidista = MovimientoParacaidista(this)
    val persona = BitmapFactory.decodeResource(principal.resources, R.drawable.paracaida)
    val nube1 = BitmapFactory.decodeResource(principal.resources, R.drawable.nube)
    val nube2 = BitmapFactory.decodeResource(principal.resources, R.drawable.nube)

    init{
        movimientoParacaidista.start()
    }

    override fun onDraw(c: Canvas){
        super.onDraw(c)
        val p = Paint()

        //fondo
        p.color = Color.rgb(colorR,139,139)
        c.drawRect(0f,0f,1950f,1950f,p)


        //nubes 2
        c.drawBitmap(nube2,800f,coorY+400,p)

        //paracaidista
        c.drawBitmap(persona, coorX,250f,p)

        //nubes
        c.drawBitmap(nube1,30f,coorY,p)


        coorY = coorY-30f
        if(coorY < -700f){
            coorY = 2000f
        }//if
    }//onDraw

}

class MovimientoParacaidista(p:Lienzo):Thread(){
    val puntero = p

    override  fun run(){
        super.run()
        while(true){
            puntero.principal.runOnUiThread{
                puntero.invalidate()
            }
            sleep(50)
        }
    }
}