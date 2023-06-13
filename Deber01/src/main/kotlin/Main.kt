import sun.jvm.hotspot.oops.CellTypeState.value
import java.io.File
import java.util.ArrayList

fun main(args: Array<String>) {

    val pathSistemasSolares = "C:/Users/escritorio.virtual9/Documents/GitHub/mov-com-2023A/herrera-mosquera-elisa-andrea-mov-com/00-kotlin/Deber01/src/main/kotlin/SistemaSolar.kt"
    val pathPlanetas = "C:/Users/escritorio.virtual9/Documents/GitHub/mov-com-2023A/herrera-mosquera-elisa-andrea-mov-com/00-kotlin/Deber01/src/main/kotlin/Planeta.kt"
    var flag = true
    val fileManager = FileManager()

    while (flag) {
        println(
            "¡Bienvenido! ¿Qué acción te gustaría realizar?\n" +
                    "1: Administrar Sistemas Solares\n" +
                    "2: Administrar Planetas\n" +
                    "3: Salir"
        )

        print("Ingresa la opción: ")
        val option = readLine()?.uppercase()

        when (option) {
            "1" -> {
                var flagSistemasSolares = true

                while (flagSistemasSolares) {
                    println(
                        "¿Qué acción te gustaría realizar en Sistemas Solares?\n" +
                                "A: Agregar Sistema Solar\n" +
                                "B: Listar Sistemas Solares\n" +
                                "C: Buscar Sistema Solar\n" +
                                "D: Eliminar Sistema Solar\n" +
                                "E: Guardar Sistemas Solares en un archivo\n" +
                                "F: Regresar\n" +
                                "G: Salir"
                    )
                    print("Ingresa la opción: ")
                    var optionSistemasSolares = readLine()?.uppercase()

                    when (optionSistemasSolares) {
                        "A" -> {

                            print("\nIngresa la edad: ")
                            var value = readLine()
                            val edad: Int? = value?.toInt()
                            print("\nIngresa el nombre: ")
                            value = readLine()
                            val nombre: String = value.toString()
                            print("\nIngresa el tipo: ")
                            value = readLine()
                            val tipo: String = value.toString()
                            print("\nIngresa la galaxia: ")
                            value = readLine()
                            val galaxia: String = value.toString()

                            val content:String= "$edad-$nombre $tipo $galaxia"

                            fileManager.saveTextFile(fileName = "$pathSistemasSolares$nombre.txt", content = content)
                            println("\nSe ha guardado con éxito!\n")
                        }
                        "B" -> {
                            // Listar sistemas solares
                            // ...
                        }
                        "E" -> {
                            // Guardar sistemas solares en un archivo de texto
                            val listOfFiles = fileManager.listTextFile(pathSistemasSolares)
                            val content = listOfFiles.joinToString("\n")
                            fileManager.saveTextFile("$pathSistemasSolares/sistemas_solares.txt", content)
                            println("\nLa lista de sistemas solares se ha guardado en un archivo de texto.\n")
                        }
                        "F" -> {
                            flagSistemasSolares = false
                        }
                        "G" -> {
                            flagSistemasSolares = false
                            flag = false
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
                        "¿Qué acción te gustaría realizar en Planetas?\n" +
                                "A: Agregar Planeta\n" +
                                "B: Buscar Planeta\n" +
                                "C: Eliminar Planeta\n" +
                                "D: Guardar Planetas en un archivo\n" +
                                "E: Regresar\n" +
                                "F: Salir"
                    )
                    print("Ingresa la opción: ")
                    var optionPlanetas = readLine()?.uppercase()

                    when (optionPlanetas) {
                        "A" -> {
                            // Agregar un planeta
                            // ...
                        }
                        "D" -> {
                            // Guardar planetas en un archivo de texto
                            val listOfFiles = fileManager.listTextFile(pathPlanetas)
                            val content = listOfFiles.joinToString("\n")
                            fileManager.saveTextFile("$pathPlanetas/planetas.txt", content)
                            println("\nLa lista de planetas se ha guardado en un archivo de texto.\n")
                        }
                        "E" -> {
                            flagPlanetas = false
                        }
                        "F" -> {
                            flagPlanetas = false
                            flag = false
                        }
                        else -> {
                            println("Opción inválida seleccionada.")
                        }
                    }
                }
            }

            "3" -> {
                flag = false
                println("¡Gracias!")
            }

            else -> {
                println("Opción inválida seleccionada.")
            }
        }
    }
}

class FileManager {

    constructor(){

    }


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
