package com.example.examen01

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planetas")
class Planeta(
    @PrimaryKey var planeta: String,
    @ColumnInfo(name = "edadPlaneta") var edadPlaneta: String,
   // @ColumnInfo(name = "galaxia") var galaxia:String,
    @ColumnInfo(name = "descripcionP") var descripcionP: String,
    //@ColumnInfo(name = "masa") var masa: String,
    @ColumnInfo(name ="codigoSistema") var codigoSistema: Int,

)
