package contructores

class SistemaSolar{
    var edad: Int = 0
    var nombre: String = ""

    var tipo: String = ""
    var galaxia: String =""
    var descripcion: String =""

    constructor(edad: Int, nombre: String, tipo: String, galaxia: String, descripcion: String) {
        this.edad = edad
        this.nombre = nombre
        this.tipo = tipo
        this.galaxia = galaxia
        this.descripcion = descripcion
    }
    override fun toString(): String {
        return """
        Sistema Details:
        edad: $edad
        nombre: $nombre
        tipo: $tipo
        galaxia: $galaxia
        descripcion: $descripcion
    """.trimIndent()
    }
}