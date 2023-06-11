class SistemaSolar(
    var edad: Int,
    var nombre: String,

    var tipo: String,
    var galaxia: String
) {
    var planetas: MutableList<Planeta> = mutableListOf()

    fun agregarPlaneta(planeta: Planeta) {
        planetas.add(planeta)
    }

    fun mostrarPlanetas() {
        if (planetas.isEmpty()) {
            println("El sistema solar no contiene planetas.")
        } else {
            println("Edad: $edad años")
            println("Sistema Solar: $nombre")

            println("Tipo: $tipo")
            println("Galaxia: $galaxia")
            println()
            for (planeta in planetas) {
                println("ID: ${planeta.idPlaneta}")
                println("Nombre: ${planeta.nombrePlaneta}")
                println("Número de Lunas: ${planeta.numeroLunas}")
                println("Diámetro: ${planeta.diametro}")
                println("Masa: ${planeta.masa}")
                println("Distancia media al Sol: ${planeta.distanciaMediaSol}")
                println()
            }
        }
    }

    companion object {
        fun agregarPlaneta(planeta: Planeta) {
            TODO("Not yet implemented")
        }
    }

}