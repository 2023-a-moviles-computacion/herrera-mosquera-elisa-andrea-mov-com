package Clases

class SistemaSolar {
    var edad: Int? = 0;
    var nombre: String =" ";
    var tipo: String  =" ";
    var galaxia: String =" ";
    var distancia: Double  =0.0

    constructor(edad: Int?, nombre:String, tipo:String, galaxia:String,distancia:Double) { // Bloque de codigo del constructor
        this.edad =  edad
        this.nombre =nombre
        this.tipo = tipo
        this.galaxia = galaxia
        this.distancia = distancia
    }
    override fun toString(): String {
        return """
           Sistema Solar Informacion
            edad: $edad
            nombre: $nombre
            tipo: $tipo 
            galaxia: $galaxia
            distancia: $distancia
            """.trimIndent()
    }

    fun add(sistemaSolar: SistemaSolar) {


    }
}

