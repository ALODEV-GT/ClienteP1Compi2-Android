package com.example.pruebaclientemusic
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaclientemusic.message.SimpleMessage
import com.example.pruebaclientemusic.task.AsyncResponse
import com.example.pruebaclientemusic.task.MyTask

class MainActivity : AppCompatActivity(), AsyncResponse {

    //Socket
    private val ipServer: String = "192.168."
    private val portServer: Int = 9090
    private lateinit var ipEt: EditText

    private lateinit var conectarBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val error: String? = intent.getStringExtra("error")
        if (error != null){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        conectarBtn = findViewById(R.id.conectarBtn)
        ipEt = findViewById(R.id.ipEt)

        conectarBtn.setOnClickListener {

            if(ipEt.text.trim().isEmpty()){
                Toast.makeText(this,"Ingresa una ip valida", Toast.LENGTH_SHORT).show()
            }else{
                val intent: Intent = Intent(this, teclado:: class.java)
                intent.putExtra("ip", ipServer+ipEt.text)
                startActivity(intent)
            }
        }
    }

    private fun sendMessage(message: String) {
        val simpleMessage = SimpleMessage(message)
        val task = MyTask(ipServer, portServer, simpleMessage)
        task.delegate = this
        task.execute()
    }

    override fun processResponse(output: String?) {
        if (output != null) {
            println(output)
        } else {
            println("Something went wrong")
        }
    }
}