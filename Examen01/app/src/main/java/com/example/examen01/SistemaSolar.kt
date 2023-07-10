package com.example.examen01

import java.io.File

data class SistemaSolar(
    var nombre: String,
    var edad: Int? = 0,
    var galaxia: String =" ",
    var distancia: Double  =0.0,
    var descripcion: String =" "
){
    companion object {
        private const val archivo_sistemsolares =
        fun desplegarSistemaSolar(): List<SistemaSolar> {
            val archivo_sistemasolares = File(archivo_sistemaSolares)
            val gson = Gson()
            val lineas = archivo_sistemsolares.readLines()
            val sistemaSolares = mutableListOf<SistemaSolar>()

            for (linea in lineas) {
                val sistemaSolar = gson.fromJson(linea, SistemaSolar::class.java)
                SistemaSolares.add(sistemaSolar)
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
            val sistemaSolarBorrar = cocineros.find { it.id == id }
            if (sistemaSolarBorrar != null) {
                sistemaSolares.remove(sistemaSolarBorrar)
                guardarSistemaSolar(sistemaSolares)
            }
        }

        private fun guardarSistemaSolar(SistemaSolares: List<SistemaSolar>) {
            val archivoSistemaSolares = File(archivo_sistemsolares)
            val gson = Gson()
            val lineas = sistemaSolares.map { gson.toJson(it) }
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

        return "SistemaSolar #$id \nNombre: $nombre \nEdad: $edad \ngalaxia: $galaxia\nDistancia: $distancia\ndescripcion: $descripcion\nPlanetas: ${Planetas.contentToString()}\n"
    }
}

