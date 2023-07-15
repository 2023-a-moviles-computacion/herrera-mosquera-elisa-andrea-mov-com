package clases


import contructores.Planeta
import contructores.SistemaSolar
import java.io.File

fun main(args: Array<String>) {
    val heladerias = ArrayList<SistemaSolar>()
    val helados = ArrayList<Planeta>()
    val fileManager = FileManager()
    val pathSistemaSolar = "C:/User/escritorio.virtual9/Documents/GitHub/mov-com-2023A/herrera-mosquera-elisa-andrea-mov-com/00-kotlin/Deber01/src/main/kotlin/Outputs/SitemaSolar.txt"
    val pathPlaneta= "C:/User/escritorio.virtual9/Documents/GitHub/mov-com-2023A/herrera-mosquera-elisa-andrea-mov-com/00-kotlin/Deber01/src/main/kotlin/Outputs/Planeta.txt"

    while (true) {
        println(
            "Seleciona una opcion\n" +
                    "1: Sistema Solar\n" +
                    "2: Planeta\n" +
                    "3: Salir"
        )
        print("Ingresa la opción: ")
        when (readlnOrNull()?.uppercase()) {
            "1" -> {
                var flagSistemaSolar = true
                while (flagSistemaSolar) {
                    println(
                        "Seleciona la opcion a realizar en la Heladeria\n" +
                                "1: Agregar Heladeria y Guardar Heladerias en un archivo\n" +
                                "2: Listar Heladerias\n" +
                                "3: Buscar Heladeria\n" +
                                "4: Eliminar Heladeria\n" +
                                "5: Regresar\n" +
                                "6: Salir"
                    )
                    print("Ingresa la opción: \n")
                    var optionHeladerias = readlnOrNull()?.uppercase()
                    when (optionHeladerias) {
                        "1" -> {
                            val heladeria = ingresarHeladeria()
                            heladerias.add(heladeria)
                            println("Heladeria agregada exitosamente.\n")
                            val content = heladerias.joinToString("\n\n") { it.toString() }
                            fileManager.saveTextFile(fileName = pathHeladeria, content = content)

                            println("\nLa lista de heladerias se ha guardado en un archivo de texto.\n")
                        }
                        "2" -> {
                            println("\nListado de Heladerias:")
                            for (heladeria in heladerias) {
                                println("Nombre: ${heladeria.nombre}")
                                println("Direccion: ${heladeria.direccion}")
                                println("Telefono: ${heladeria.telefono}")
                                println("Calificacion: ${heladeria.calificacion}")
                                println("Capacidad: ${heladeria.capacidad}\n")
                            }
                        }
                        "3" -> {
                            print("\nIngresa el nombre de la Heladeria a buscar: ")
                            val nombreBusqueda = readlnOrNull().toString()
                            val resultadoBusqueda = heladerias.filter { it.nombre.equals(nombreBusqueda, ignoreCase = true) }
                            if (resultadoBusqueda.isNotEmpty()) {
                                println("\nHeladeria encontrado:")
                                val heladeriaEncontrado = resultadoBusqueda.first()
                                println("Nombre: ${heladeriaEncontrado.nombre}")
                                println("Direccion: ${heladeriaEncontrado.direccion}")
                                println("Telefono: ${heladeriaEncontrado.telefono}")
                                println("Calificacion: ${heladeriaEncontrado.calificacion}")
                                println("Capacidad: ${heladeriaEncontrado.capacidad}\n")
                            } else {
                                println("\nNo se encontró ningúna Heladeria con ese nombre.\n")
                            }
                        }
                        "4" -> {
                            print("\nIngresa el nombre de la heladeria a eliminar: ")
                            val nombreEliminacion = readlnOrNull().toString()
                            val resultadoEliminacion = heladerias.removeIf { it.nombre.equals(nombreEliminacion, ignoreCase = true) }
                            if (resultadoEliminacion) {
                                println("\nHeladeria eliminado exitosamente.\n")
                            } else {
                                println("\nNo se encontró ningúna heladeria con ese nombre.\n")
                            }
                        }
                        "5" -> {
                            flagSistemaSolar = false
                        }
                        "6" -> {
                            flagSistemaSolar = false
                            return
                        }
                        else -> {
                            println("Opción inválida seleccionada.")
                        }
                    }
                }
            }
            "2" -> {
                var flagHelados = true
                while (flagHelados) {
                    println(
                        "Seleciona la opcion a realizar en Helados\n" +
                                "1: Agregar Helado y guardar en un archivo \n" +
                                "2: Listar Helados\n" +
                                "3: Buscar Helados\n" +
                                "4: Eliminar Helados\n" +
                                "5: Regresar\n" +
                                "6: Salir"
                    )
                    print("Ingresa la opción: ")
                    var optionHelados = readlnOrNull()?.uppercase()
                    when (optionHelados) {
                        "1" -> {
                            val helado = ingresarHelado()
                            helados.add(helado)
                            println("\nHelado agregado exitosamente.\n")

                            val content = helados.joinToString("\n") { it.toString() }
                            fileManager.saveTextFile(fileName = pathHelado, content = content)
                            println("\nLa lista de healdos se ha guardado en un archivo de texto.\n")
                        }
                        "2" -> {
                            println("\nListado de Helados:")
                            for (helado in helados) {
                                println("Nombre: ${helado.nombreHelado}")
                                println("ID: ${helado.idHelado}")
                                println("Sabor 1: ${helado.sabor1}")
                                println("Sabor 2: ${helado.sabor2}")
                                println("Precio: ${helado.precio}")
                                println("Chispas: ${helado.chispas}")
                            }
                        }
                        "3" -> {
                            print("\nIngresa el nombre del helado a buscar: ")
                            val nombreBusqueda = readlnOrNull().toString()
                            val resultadoBusqueda = helados.filter { it.nombreHelado.equals(nombreBusqueda, ignoreCase = true) }
                            if (resultadoBusqueda.isNotEmpty()) {
                                println("\nHelado encontrado:")
                                val heladoEncontrado = resultadoBusqueda.first()
                                println("Nombre: ${heladoEncontrado.nombreHelado}")
                                println("ID: ${heladoEncontrado.idHelado}")
                                println("Sabor 1: ${heladoEncontrado.sabor1}")
                                println("Sabor 2: ${heladoEncontrado.sabor2}")
                                println("Precio: ${heladoEncontrado.precio}")
                                println("Chispas: ${heladoEncontrado.chispas}")
                            } else {
                                println("\nNo se encontró ningún helado con ese nombre.\n")
                            }
                        }
                        "4" -> {
                            print("\nIngresa el nombre del helado a eliminar: ")
                            val nombreEliminacion = readLine().toString()
                            val resultadoEliminacion = helados.removeIf { it.nombreHelado.equals(nombreEliminacion, ignoreCase = true) }
                            if (resultadoEliminacion) {
                                println("\nHelado eliminado exitosamente.\n")
                            } else {
                                println("\nNo se encontró ningún helado con ese nombre.\n")
                            }
                        }
                        "5" -> {
                            flagHelados = false
                        }
                        "6" -> {
                            flagHelados = false
                            return
                        }
                        else -> {
                            println("Opción inválida seleccionada.")
                        }
                    }
                }
            }
            "3" -> {
                println("Gracias!")
                return
            }
            else -> {
                println("Opción inválida seleccionada.")
            }
        }
    }
}
fun ingresarHeladeria(): Heladeria {
    println("\n--- Ingresar Heladeria ---")
    print("Nombre: ")
    val nombre = readlnOrNull().toString()
    print("Direccion: ")
    val direccion = readlnOrNull().toString()
    print("Telefono: ")
    val telefono = readlnOrNull().toString()
    print("Calificacion: ")
    val calificacion = readlnOrNull()?.toDouble()
    print("Capacidad: ")
    val capacidad = readlnOrNull()?.toInt()

    return Heladeria( nombre, direccion, telefono, calificacion?: 0.0, capacidad?: 0.0)
}

fun ingresarHelado(): Helados {
    println("\n--- Ingresar Helado ---")
    print("ID del Helado: ")
    val idHelado = readlnOrNull()?.toInt()
    print("Nombre del Helado: ")
    val nombreHelado = readlnOrNull().toString()
    print("Primer Sabor: ")
    val sabor1 = readlnOrNull().toString()
    print("Segundo Sabor: ")
    val sabor2 = readlnOrNull().toString()
    print("Precio: ")
    val precio = readlnOrNull()?.toDouble()
    print("Chispas: ")
    val chispas = readlnOrNull()?.toInt()

    return Helados (idHelado?: 0.0, nombreHelado, sabor1, sabor2, precio ?: 0.0, chispas?: 0.0)
}
class FileManager {
    fun saveTextFile(fileName: String, content: String) {
        val file = File(fileName)
        file.writeText(content)
    }
    fun readTextFile(fileName: String): String {
        val file = File(fileName)
        return file.readText()
    }
}
