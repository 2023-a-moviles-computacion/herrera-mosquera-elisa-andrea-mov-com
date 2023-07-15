package contructores

class Planeta{
    var idPlaneta: String = "";
    var nombrePlaneta: String = "";
    var numeroLunas: Int = 0;
    var diametro: Double = 0.0;
    var masa: Double = 0.0;
    var distanciaMediaSol: Double = 0.0;

    constructor(
        idPlaneta: String,
        nombrePlaneta: String,
        numeroLunas: Int,
        diametro: Double,
        masa: Double,
        distanciaMediaSol: Double
    ) {
        this.idPlaneta = idPlaneta
        this.nombrePlaneta = nombrePlaneta
        this.numeroLunas = numeroLunas
        this.diametro = diametro
        this.masa = masa
        this.distanciaMediaSol = distanciaMediaSol
    }

    override fun toString(): String {
        return """
        Planeta Details:
        
        idPlaneta: $idPlaneta
        nombrePlaneta: $nombrePlaneta
        numeroLunas: $numeroLunas
        diametro: $diametro
        masa:= $masa
        distanciaMediaSol: $distanciaMediaSol
        """.trimIndent()
    }
}