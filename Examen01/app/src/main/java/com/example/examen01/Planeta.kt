package com.example.examen01


    import java.io.File
    import java.text.SimpleDateFormat
    import java.util.*
    import com.google.gson.Gson


    class Planeta(
        val id: Int,
        val nombrePlaneta: String = "",
        val numeroLunas: Int = 0,
        val diametro: Double = 0.0,
        val masa: Double = 0.0,
    )
    {
        companion object {
        private val archivo_planetas = "C:\\Users\\escritorio.virtual9\\Documents\\GitHub\\mov-com-2023A\\herrera-mosquera-elisa-andrea-mov-com\\Examen01\\app\\src\\main\\java\\com\\example\\examen01\\kotlin\\Kotlin\\planeta.txt"


        fun desplegarPlanetas(): List<Planeta> {
            val archivoPlanetas = File(archivo_planetas)
            val gson = Gson()
            val lineas = archivoPlanetas.readLines()
            val planetas = mutableListOf<Planeta>()

            for (linea in lineas) {
                val planeta = gson.fromJson(linea, Planeta::class.java)
                planetas.add(planeta)
            }

            return planetas
        }

        fun crearPlaneta(planeta: Planeta) {
            val planetas = desplegarPlanetas().toMutableList()
            planetas.add(planeta)
            guardarPlaneta(planetas)
        }

        fun idDisponible(): Int {
            val planetas = desplegarPlanetas()
            return planetas.map { it.id }.maxOrNull()?.plus(1) ?: 1
        }

        fun borrarPlaneta(id: Int) {
            val planetas = desplegarPlanetas().toMutableList()
            val planetaBorrar = planetas.find { it.id == id }
            if (planetaBorrar != null) {
                planetas.remove(planetaBorrar)
                guardarPlaneta(planetas)
            }
        }

        fun guardarPlaneta(planetas: List<Planeta>) {
            val archivoPlanetas = File(archivo_planetas)
            val gson = Gson()
            val lineas = planetas.map { gson.toJson(it) }
            archivoPlanetas.writeText(lineas.joinToString("\n"))
        }

        fun actualizarPlaneta(planeta: Planeta) {
            val planetas = Planeta.desplegarPlanetas().toMutableList()
            val index = planetas.indexOfFirst { it.id == planeta.id }
            if (index != -1) {
                planetas[index] = planeta
                Planeta.guardarPlaneta(planetas)
            }
        }


}

        override fun toString(): String {
            return """
            Planeta #$id
            Nombre: $nombrePlaneta
            NumeroLunas: $numeroLunas
            Diametro: $diametro
            Masa: $masa
           
        """.trimIndent()
        }
    }