package com.example.aciclovida

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Elisa", "e@e.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Andrea", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Ron", "r@r.com")
                )
        }
    }
}