package com.example.examen01

import android.graphics.Insets.add
import androidx.core.graphics.Insets.add
import androidx.core.view.OneShotPreDrawListener.add
import com.google.android.gms.common.util.WorkSourceUtil.add
import com.google.gson.Gson
import java.io.File

data class SistemaSolar(
    val id: Int,
    val nombre: String,
    val edad: Int? = 0,
    val  galaxia: String =" ",
    val distancia: Double  =0.0,
    val descripcion: String =" "
){
    companion object {
        private const val archivo_sistemaSolares = "C:\\Users\\escritorio.virtual9\\Documents\\GitHub\\mov-com-2023A\\herrera-mosquera-elisa-andrea-mov-com\\Examen01\\app\\src\\main\\java\\com\\example\\examen01\\kotlin\\Kotlin\\sistemasolar.txt"
        fun desplegarSistemaSolar(): List<SistemaSolar> {
            val archivo_sistemasolares = File(archivo_sistemaSolares)
            val gson = Gson()
            val lineas = archivo_sistemasolares.readLines()

            val sistemaSolares = mutableListOf<SistemaSolar>()

            for (linea in lineas) {
                val sistemaSolar = gson.fromJson(linea, SistemaSolar::class.java)
               sistemaSolares.add(sistemaSolar)
            }

            return sistemaSolares
        }



        fun crearSistemaSolar(sistemaSolar: SistemaSolar) {
            val sistemaSolares = desplegarSistemaSolar().toMutableList()
            sistemaSolares.add(sistemaSolar)
            guardarSistemaSolar(sistemaSolares)
        }

        fun idDisponible(): Int {
            val sistemaSolares = desplegarSistemaSolar()
            return sistemaSolares.map { it.id }.maxOrNull()?.plus(1) ?: 1
        }

        fun borrarSistemaSolar(id: Int) {
            val sistemaSolares = desplegarSistemaSolar().toMutableList()
            val sistemaSolarBorrar = sistemaSolares.find { it.id == id }
            if (sistemaSolarBorrar != null) {
                sistemaSolares.remove(sistemaSolarBorrar)
                guardarSistemaSolar(sistemaSolares)
            }
        }

        private fun guardarSistemaSolar(SistemaSolares: List<SistemaSolar>) {
            val archivoSistemaSolares = File(archivo_sistemaSolares)
            val gson = Gson()
            val lineas = archivo_sistemaSolares.map { gson.toJson(it) }
            archivoSistemaSolares.writeText(lineas.joinToString("\n"))
        }

        fun actualizarSistemaSolar(sistemaSolar: SistemaSolar) {
            val sistemaSolares = desplegarSistemaSolar().toMutableList()
            val index = sistemaSolares.indexOfFirst { it.id == sistemaSolar.id }
            if (index != -1) {
                sistemaSolares[index] = sistemaSolar
                guardarSistemaSolar(sistemaSolares)
            }
        }
    }

    override fun toString(): String {

        return "SistemaSolar #$id \nNombre: $nombre \nEdad: $edad \ngalaxia: $galaxia\nDistancia: $distancia\ndescripcion: $descripcion\nPlanetas: \n"
    }
}


