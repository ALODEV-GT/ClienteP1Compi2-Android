package com.example.pruebaclientemusic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CreacionPistaText : AppCompatActivity() {
    private lateinit var backBtn: Button
    private lateinit var codigoFuenteTv: TextView
    private var ipServer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creacion_pista_text)
        ipServer = intent.getStringExtra("ip").toString()

        backBtn = findViewById(R.id.backBtn)
        codigoFuenteTv = findViewById(R.id.codigoFuenteTv)

        val intent: Intent = this.intent
        val codigoFuente: String? = intent.getStringExtra("codigoFuente")
        codigoFuenteTv.text=codigoFuente

        backBtn.setOnClickListener{
            val intent = Intent(this, teclado:: class.java)
            intent.putExtra("ip", ipServer)
            startActivity(intent)
        }
    }
}