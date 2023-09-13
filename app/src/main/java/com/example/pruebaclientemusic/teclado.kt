package com.example.pruebaclientemusic

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaclientemusic.message.SimpleMessage
import com.example.pruebaclientemusic.task.AsyncResponse
import com.example.pruebaclientemusic.task.MyTask
import com.example.pruebaclientemusic.tecladoMembers.Canal
import com.example.pruebaclientemusic.tecladoMembers.Nota

class teclado : AppCompatActivity(), AsyncResponse {
    //Botones notas
    private lateinit var doBtn: Button
    private lateinit var doSBtn: Button
    private lateinit var reBtn: Button
    private lateinit var reSBtn: Button
    private lateinit var miBtn: Button
    private lateinit var faBtn: Button
    private lateinit var faSBtn: Button
    private lateinit var solBtn: Button
    private lateinit var solSBtn: Button
    private lateinit var laBtn: Button
    private lateinit var laSBtn: Button
    private lateinit var siBtn: Button
    private lateinit var silencioBtn: Button

    //Tiempo y Selector de octavas
    lateinit var chronometer: Chronometer
    lateinit var octavasRg: RadioGroup
    lateinit var tiempoTv: TextView


    //Controlador tiempo
    private var isRunning = false
    private val handler = Handler()

    //Valores
    private lateinit var nombreEt: EditText
    private lateinit var canalEt: EditText
    private var octava = 1
    private var tiempo = 0

    //Canales
    private var canales = mutableMapOf<Int,Canal>()

    //Ejecucion
    private lateinit var guardarBtn: Button

    //Sockets
    private var ipServer: String = ""
    private val portServer: Int = 9090


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teclado)

        //Recibiendo ip
        ipServer = intent.getStringExtra("ip").toString()

        //Referencia a botones
        doBtn = findViewById(R.id.doBtn)
        doSBtn = findViewById(R.id.doSBtn)
        reBtn = findViewById(R.id.reBtn)
        reSBtn = findViewById(R.id.reSBtn)
        miBtn = findViewById(R.id.miBtn)
        faBtn = findViewById(R.id.faBtn)
        faSBtn = findViewById(R.id.faSBtn)
        solBtn = findViewById(R.id.solBtn)
        solSBtn = findViewById(R.id.solSBtn)
        laBtn = findViewById(R.id.laBtn)
        laSBtn = findViewById(R.id.laSBtn)
        siBtn = findViewById(R.id.siBtn)
        silencioBtn = findViewById(R.id.silencioBtn)

        chronometer = findViewById(R.id.chronometer)
        octavasRg = findViewById(R.id.octavasRg)
        tiempoTv = findViewById(R.id.tiempoTv)
        chronometer.visibility = View.GONE
        canalEt = findViewById(R.id.canalEd)
        nombreEt = findViewById(R.id.nombreEt)
        guardarBtn = findViewById(R.id.guardarBtn)

        doBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Do", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        doSBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Do#", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        reBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Re", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        reSBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Re#", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        miBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Mi", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        faBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Fa", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        faSBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Fa#", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        solBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Sol", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        solSBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Sol#", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        laBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("La", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        laSBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("La#", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        siBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("Si", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        silencioBtn.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isRunning) {
                        chronometer.base = SystemClock.elapsedRealtime();
                        chronometer.start()
                        isRunning = true
                        handler.post(updateTimeRunnable)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isRunning){
                        chronometer.stop();
                        tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                        isRunning = false
                        handler.removeCallbacks(updateTimeRunnable)

                        agregarNota(Nota("R", octava, tiempo.toLong(), canalEt.text.toString().toIntOrNull() ?: 0))
                    }
                }
            }
            true
        }

        val defaultOption = findViewById<RadioButton>(R.id.octava1Rb)
        defaultOption.isChecked = true

        octavasRg.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            when (selectedRadioButton.text.toString()) {
                "1" ->
                    octava = 1
                "2" ->
                    octava = 2
                "3" ->
                    octava = 3
                "4" ->
                    octava = 4
                "5" ->
                    octava = 5
                "6" ->
                    octava = 6
                "7" ->
                    octava = 7
                "8" ->
                    octava = 8
            }
        })

        guardarBtn.setOnClickListener{
            guardar()
        }

        /* //Empieza a contar cuando se presione y se detiene cuando se vuelve a presionar
        doBtn.setOnClickListener{
            if (!isRunning){
                chronometer.base = SystemClock.elapsedRealtime();
                chronometer.start()
                isRunning = true
                handler.post(updateTimeRunnable)
            }else{
                chronometer.stop();
                tiempo = (SystemClock.elapsedRealtime() - chronometer.base).toInt()
                isRunning = false
                handler.removeCallbacks(updateTimeRunnable)
                println( "Nota Do, Octava: $octava, Tiempo: $tiempo")
            }
        }*/
    }

    private val updateTimeRunnable: Runnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                val currentTime = SystemClock.elapsedRealtime() - chronometer.base
                tiempoTv.text = "$currentTime ms"
                handler.postDelayed(this, 1) // Actualizar cada 1 milisegundos
            }
        }
    }

    private fun guardar(){

        //Validaciones
        if (nombreEt.text.trim().isEmpty()) {
            Toast.makeText(this,"Ingresa un nombre", Toast.LENGTH_SHORT).show()
            return
        }

        if (canales.isEmpty()){
            Toast.makeText(this,"Presiona almenos una nota", Toast.LENGTH_SHORT).show()
            return
        }

        //Enviando al servidor
        val codigoFuente: String = crearPista()
        this.sendMessage(codigoFuente)

        val intent = Intent(this, CreacionPistaText:: class.java)
        intent.putExtra("codigoFuente",codigoFuente)
        intent.putExtra("ip", ipServer)
        startActivity(intent)
    }

    private fun sendMessage(message: String) {
        val simpleMessage = SimpleMessage(message)
        val task = MyTask(ipServer, portServer, simpleMessage)
        task.delegate = this
        task.execute()
    }

    private fun crearPista(): String{
        var codigoFuente: String = "<solicitud>\n\t<tipo>${nombreEt.text}</tipo>\n"
        for ((clave, valor) in canales){
            val canal: Canal = valor
            codigoFuente+= canal.getCodigoFuenteNotas()
        }
        codigoFuente+="</solicitud>"
        return codigoFuente
    }

    private fun agregarNota(nota: Nota) {
        val can: Canal? = canales[nota.canal]
        if (can != null) {
            //Agregar al canal
            can.agregarNota(nota)
        } else {
            //Crear el canal y agregar
            canales[nota.canal] = Canal(nota.canal)
            val canal: Canal? = canales[nota.canal]
            canal!!.agregarNota(nota)
        }
    }

    override fun processResponse(output: String?) {
        if (output != null) {
            Toast.makeText(this, output, Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, MainActivity:: class.java)
            intent.putExtra("error", "No se pudo conectar al servidor")
            startActivity(intent)
        }
    }
}