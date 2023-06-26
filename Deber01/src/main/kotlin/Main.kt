
import Clases.Planeta
import Clases.SistemaSolar
import java.io.File

fun main(args: Array<String>) {
    val sistemasSolares = ArrayList<SistemaSolar>()
    val planetas = ArrayList<Planeta>()
    val fileManager = FileManager()
    val pathSistemaSolar = "/Users/escritorio.virtual9/Documents/GitHub/mov-com-2023A/herrera-mosquera-elisa-andrea-mov-com/Deber01/src/main/kotlin/archivos/sistemasolar.txt"
    val pathPlaneta = "/Users/escritorio.virtual9/Documents/GitHub/mov-com-2023A/herrera-mosquera-elisa-andrea-mov-com/Deber01/src/main/kotlin/archivos/planetas.txt"




    while (true) {
        println(

            "Hola!!" +
                    "Seleciona una opcion\n" +
                    "1: Sistemas Solares\n" +
                    "2: Planetas\n" +
                    "3: Salir"
        )

        print("Ingresa la opción: ")
        val option = readlnOrNull()?.uppercase()

        when (option) {
            "1" -> {
                var flagSistemasSolares = true

                while (flagSistemasSolares) {
                    println(
                        "Seleciona la opcion a realizar en Sistema Solar\n" +
                                "1: Agregar Sistema Solar y Guardar Sistemas Solares en un archivo\n" +
                                "2: Listar Sistemas Solares\n" +
                                "3: Buscar Sistema Solar\n" +
                                "4: Eliminar Sistema Solar\n" +

                                "5: Regresar\n" +
                                "6: Salir"
                    )
                    print("Ingresa la opción: ")
                    var optionSistemasSolares = readLine()?.uppercase()

                    when (optionSistemasSolares) {
                        "1" -> {
                            val sistemaSolar = ingresarSistemaSolar()
                            sistemasSolares.add(sistemaSolar)
                            println("\nSistema Solar agregado exitosamente.\n")

                            val content = sistemasSolares.joinToString("\n") { it.toString() }
                            fileManager.saveTextFile(fileName = pathSistemaSolar, content = content)
                            println("\nLa lista de sistemas solares se ha guardado en un archivo de texto.\n")
                        }
                        "2" -> {

                            println("\nListado de Sistemas Solares:")
                            for (sistemaSolar in sistemasSolares) {
                                println("Nombre: ${sistemaSolar.nombre}")
                                println("Edad: ${sistemaSolar.edad}")
                                println("Tipo: ${sistemaSolar.tipo}")
                                println("Galaxia: ${sistemaSolar.galaxia}")
                                println("Distancia: ${sistemaSolar.distancia}\n")
                            }
                            val listOfFiles: ArrayList<String> = fileManager.listTextFile(pathSistemaSolar)
                            for (name in listOfFiles) {
                                val num:Int = name.indexOf(name)+1
                                println("$num: $name")

                            }
                        }

                        "3" -> {
                            print("\nIngresa el nombre del Sistema Solar a buscar: ")
                            val nombreBusqueda = readlnOrNull().toString()
                            val resultadoBusqueda = sistemasSolares.filter { it.nombre.equals(nombreBusqueda, ignoreCase = true) }
                            if (resultadoBusqueda.isNotEmpty()) {
                                println("\nSistema Solar encontrado:")
                                val sistemaSolarEncontrado = resultadoBusqueda.first()
                                println("Nombre: ${sistemaSolarEncontrado.nombre}")
                                println("Edad: ${sistemaSolarEncontrado.edad}")
                                println("Tipo: ${sistemaSolarEncontrado.tipo}")
                                println("Galaxia: ${sistemaSolarEncontrado.galaxia}")
                                println("Distancia: ${sistemaSolarEncontrado.distancia}\n")
                            } else {
                                println("\nNo se encontró ningún Sistema Solar con ese nombre.\n")
                            }
                        }
                        "4" -> {
                            print("\nIngresa el nombre del Sistema Solar a eliminar: ")
                            val nombreEliminacion = readLine().toString()
                            val resultadoEliminacion = sistemasSolares.removeIf { it.nombre.equals(nombreEliminacion, ignoreCase = true) }
                            if (resultadoEliminacion) {
                                println("\nSistema Solar eliminado exitosamente.\n")
                            } else {
                                println("\nNo se encontró ningún Sistema Solar con ese nombre.\n")
                            }
                        }

                        "5" -> {
                            flagSistemasSolares = false
                        }
                        "6" -> {
                            flagSistemasSolares = false
                            return
                        }
                        else -> {
                            println("Opción inválida seleccionada.")
                        }
                    }
                }
            }

            "2" -> {
                var flagPlanetas = true

                while (flagPlanetas) {
                    println(
                        "Seleciona la opcion a realizar en Planetas\n" +
                                "1: Agregar Clases.Planeta y guardar en un archivo \n" +
                                "2: Listar Planetas\n" +
                                "3: Buscar Clases.Planeta\n" +
                                "4: Eliminar Clases.Planeta\n" +
                                "5: Regresar\n" +
                                "6: Salir"
                    )
                    print("Ingresa la opción: ")
                    var optionPlanetas = readLine()?.uppercase()

                    when (optionPlanetas) {
                        "1" -> {
                            val planeta = ingresarPlaneta()
                            planetas.add(planeta)
                            println("\nClases.Planeta agregado exitosamente.\n")

                            val content = planetas.joinToString("\n") { it.toString() }
                            fileManager.saveTextFile(fileName = pathPlaneta, content = content)
                            println("\nLa lista de planetas se ha guardado en un archivo de texto.\n")
                        }
                        "2" -> {
                            println("\nListado de Planetas:")
                            for (planeta in planetas) {
                                println("Nombre: ${planeta.nombrePlaneta}")
                                println("ID: ${planeta.idPlaneta}")
                                println("Número de Lunas: ${planeta.numeroLunas}")
                                println("Diámetro: ${planeta.diametro}")
                                println("Masa: ${planeta.masa}")

                            }
                        }
                        "3" -> {
                            print("\nIngresa el nombre del Clases.Planeta a buscar: ")
                            val nombreBusqueda = readLine().toString()
                            val resultadoBusqueda = planetas.filter { it.nombrePlaneta.equals(nombreBusqueda, ignoreCase = true) }
                            if (resultadoBusqueda.isNotEmpty()) {
                                println("\nClases.Planeta encontrado:")
                                val planetaEncontrado = resultadoBusqueda.first()
                                println("Nombre: ${planetaEncontrado.nombrePlaneta}")
                                println("ID: ${planetaEncontrado.idPlaneta}")
                                println("Número de Lunas: ${planetaEncontrado.numeroLunas}")
                                println("Diámetro: ${planetaEncontrado.diametro}")
                                println("Masa: ${planetaEncontrado.masa}")

                            } else {
                                println("\nNo se encontró ningún Clases.Planeta con ese nombre.\n")
                            }
                        }
                        "4" -> {
                            print("\nIngresa el nombre del Clases.Planeta a eliminar: ")
                            val nombreEliminacion = readLine().toString()
                            val resultadoEliminacion = planetas.removeIf { it.nombrePlaneta.equals(nombreEliminacion, ignoreCase = true) }
                            if (resultadoEliminacion) {
                                println("\nClases.Planeta eliminado exitosamente.\n")
                            } else {
                                println("\nNo se encontró ningún Clases.Planeta con ese nombre.\n")
                            }
                        }

                        "5" -> {
                            flagPlanetas = false
                        }
                        "6" -> {
                            flagPlanetas = false
                            return
                        }
                        else -> {
                            println("Opción inválida seleccionada.")
                        }
                    }
                }
            }

            "3" -> {
                println("¡Gracias!")
                return
            }

            else -> {
                println("Opción inválida seleccionada.")
            }
        }
    }
}



fun ingresarSistemaSolar(): SistemaSolar {
    println("\n--- Ingresar Sistema Solar ---")
    print("Edad: ")
    val edad = readLine()?.toInt()
    print("Nombre: ")
    val nombre = readLine().toString()
    print("Tipo: ")
    val tipo = readLine().toString()
    print("Galaxia: ")
    val galaxia = readLine().toString()
    print("Distancia: ")
    val distancia = readLine()?.toDouble()

    return SistemaSolar(edad, nombre, tipo, galaxia, distancia ?: 0.0)
}



fun ingresarPlaneta(): Planeta {
    println("\n--- Ingresar Clases.Planeta ---")
    print("ID del Clases.Planeta: ")
    val idPlaneta = readLine().toString()
    print("Nombre del Clases.Planeta: ")
    val nombrePlaneta = readLine().toString()
    print("Número de Lunas: ")
    val numeroLunas = readLine()?.toInt()
    print("Diámetro: ")
    val diametro = readLine()?.toDouble()
    print("Masa: ")
    val masa = readLine()?.toDouble()
    return Planeta(idPlaneta, nombrePlaneta, numeroLunas ?: 0, diametro ?: 0.0, masa ?: 0.0)
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

    fun listTextFile(directoryPath:String): ArrayList<String> {
        val listOfFiles: ArrayList<String> = arrayListOf<String>()
        val directory = File(directoryPath)
        val files = directory.listFiles()

        if (files != null) {
            for (file in files) {
                if (file.isFile) {
                    listOfFiles.add(file.name)
                }
            }
        }
        return listOfFiles
    }
}
