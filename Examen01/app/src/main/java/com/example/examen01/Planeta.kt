package com.example.examen01

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "planetas")
data class Planeta (
    @PrimaryKey var planeta: String,
    @ColumnInfo(name = "id") var id: Int,
    var numeroLunas: Int,
    var diametro: Double,
    var masa: Double,
)