package com.soymichel.calculadoraimc

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_personal_data.*
import java.net.URLEncoder

class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)


    }

    fun handleClickSiguiente(v: View) {
        when(v.id) {
            R.id.SiguienteButton -> {
                val intent: Intent = Intent(this, MainActivity::class.java)
                intent.putExtra("NAME", NombreEditText.text.toString())
                startActivity(intent)
            }
            R.id.TelefonoButton -> {
                val telefono:String = UrlEditText.text.toString()
                val intent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: ${telefono}"))
                startActivity(intent)
            }
            R.id.UrlButton -> {
                val url:String = UrlEditText.text.toString()
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel: ${url}"))
                startActivity(intent)
            }
            R.id.DireccionButton -> {
                val direccion: String = DireccionEditText.text.toString()
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${direccion}"))
                startActivity(intent)
            }
        }
    }
}