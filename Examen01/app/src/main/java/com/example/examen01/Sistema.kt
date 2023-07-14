package com.example.examen01

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sistemas")
data class Sistema(
    @PrimaryKey var sistema: String,
    @ColumnInfo(name = "edad") var edad: String,
    @ColumnInfo(name = "descripcion") var descripcion: String
)