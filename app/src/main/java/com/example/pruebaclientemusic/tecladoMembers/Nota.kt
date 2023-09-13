package com.example.pruebaclientemusic.tecladoMembers

import java.io.Serializable

class Nota(val nota: String, val octava: Int, val tiempo: Long, val canal: Int) :
    Serializable {
    val repMidi: Int

    init {
        repMidi = notaToMidi
    }

    private val notaToMidi: Int
        //Crear funcion para represetar nota en midi
        get() = when (nota) {
            "Do" -> 12 + octava * 12
            "Do#" -> 13 + octava * 12
            "Re" -> 14 + octava * 12
            "Re#" -> 15 + octava * 12
            "Mi" -> 16 + octava * 12
            "Fa" -> 17 + octava * 12
            "Fa#" -> 18 + octava * 12
            "Sol" -> 19 + octava * 12
            "Sol#" -> 20 + octava * 12
            "La" -> 21 + octava * 12
            "La#" -> 22 + octava * 12
            "Si" -> 23 + octava * 12
            else -> 0
        }

    override fun toString(): String {
        return "Nota(nota='$nota', octava=$octava, tiempo=$tiempo, canal=$canal, repMidi=$repMidi)"
    }

}
