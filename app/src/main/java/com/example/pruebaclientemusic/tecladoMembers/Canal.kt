package com.example.pruebaclientemusic.tecladoMembers

import java.io.Serializable

class Canal(private val numCanal: Int) : Serializable {
    private val notas = ArrayList<Nota>()
    fun agregarNota(nota: Nota) {
        if (!estaEnLaListaNegra(nota)) {
            notas.add(nota)
        }
    }

    fun getCodigoFuenteNotas(): String{
        var codigoFuente: String = ""
        for (n in notas){
            codigoFuente+="\t<datos>\n"
            codigoFuente+="\t\t<canal>$numCanal</canal>\n"
            codigoFuente+="\t\t<nota>${n.nota}</nota>\n"
            codigoFuente+="\t\t<octava>${n.octava}</octava>\n"
            codigoFuente+="\t\t<duracion>${n.tiempo}</duracion>\n"
            codigoFuente+="\t</datos>\n"
        }

        return codigoFuente
    }

    fun getNotas(): IntArray {
        val midis = IntArray(notas.size)
        for (i in midis.indices) {
            midis[i] = notas[i].repMidi
        }
        return midis
    }

    val duraciones: LongArray
        get() {
            val tiempos = LongArray(notas.size)
            for (i in tiempos.indices) {
                tiempos[i] = notas[i].tiempo
            }
            return tiempos
        }

    val tiempoTotal: Long
        get() {
            var tiempos: Long = 0
            for (i in notas.indices) {
                tiempos += notas[i].tiempo
            }
            return tiempos
        }

    private fun estaEnLaListaNegra(nota: Nota): Boolean {
        val midi = nota.repMidi
        return if (midi == 0) {
            false
        } else {
            midi < 21 || midi > 108
        }
    }
}
