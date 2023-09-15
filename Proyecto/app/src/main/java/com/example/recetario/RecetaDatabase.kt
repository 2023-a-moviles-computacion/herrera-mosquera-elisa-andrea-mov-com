package com.example.recetario

import RecetaDao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Receta::class], version = 1)
abstract class RecetaDatabase : RoomDatabase() {
    abstract fun recetaDao(): RecetaDao
}

