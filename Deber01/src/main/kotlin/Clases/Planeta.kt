package Clases

class Planeta{
    var idPlaneta: String = "";
    var nombrePlaneta: String = "";
    var numeroLunas: Int = 0;
    var diametro: Double = 0.0;
    var masa: Double = 0.0;

    constructor(
        idPlaneta: String,
        nombrePlaneta: String,
        numeroLunas: Int,
        diametro: Double,
        masa: Double,

    ) {
        this.idPlaneta = idPlaneta
        this.nombrePlaneta = nombrePlaneta
        this.numeroLunas = numeroLunas
        this.diametro = diametro
        this.masa = masa

    }

    override fun toString(): String {
        return """
           Planeta Informacion
            idPlaneta: $idPlaneta
            nombrePlaneta: $nombrePlaneta
            numeroLunas: $numeroLunas
            diametro: $diametro
            masa: $masa
            
            """.trimIndent()
    }
}