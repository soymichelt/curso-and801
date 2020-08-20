package com.soymichel.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name: String = this.intent.getStringExtra("NAME")
        this.actionBar?.title = name
        TitleTextView.text = "IMC: $name"

    }

    fun handleClickCalcularIMC(v: View) {
        val peso:Double = PesoEditText.text.toString().toDouble()
        val altura:Double = AlturaEditText.text.toString().toDouble()

        val imc: Double = (peso) / (altura * altura)

        val df: DecimalFormat = DecimalFormat()
        df.maximumFractionDigits = 2
        df.minimumFractionDigits = 2


        IMCTextView.text = "IMC: ${df.format(imc)} "

        if(imc < 18.5) RangoTextView.text = "Clasificación: ${getString(R.string.bajo_peso)}"
        if(imc >= 18.5 && imc < 25) RangoTextView.text = "Clasificación: ${getString(R.string.normal_peso)}"
        if(imc >= 25 && imc < 30) RangoTextView.text = "Clasificación: ${getString(R.string.obesidad_clase_I)}"
        if(imc >= 30 && imc < 35) RangoTextView.text = "Clasificación: ${getString(R.string.obesidad_clase_II)}"
        if(imc >= 35 && imc < 40) RangoTextView.text = "Clasificación: ${getString(R.string.obesidad_clase_III)}"
        if(imc >= 40) RangoTextView.text = "Clasificación: Obesity class III"

    }

    fun handleClickCerrar(v: View) {
        this.finish()
    }
}