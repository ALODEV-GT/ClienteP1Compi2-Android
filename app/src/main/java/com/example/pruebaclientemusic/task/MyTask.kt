package com.example.pruebaclientemusic.task

import android.os.AsyncTask
import com.example.pruebaclientemusic.message.SimpleMessage
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class MyTask(val ip: String, val port: Int, val message: SimpleMessage) : AsyncTask<Void, Void, String>() {

    var delegate: AsyncResponse? = null

    override fun doInBackground(vararg p0: Void?): String? {
        var response: SimpleMessage? = null
        try {
            val socket = Socket(ip, port)
            val outputStream = ObjectOutputStream(socket.getOutputStream())
            val inputStream = ObjectInputStream(socket.getInputStream())

            // sending message
            outputStream.writeObject(message)

            // receiving message
            response = inputStream.readObject() as SimpleMessage;
        }catch (ex: Exception){

        }

        // showing message
        return response?.message
    }

    override fun onPostExecute(result: String?) {
        delegate?.processResponse(result)
    }
}
