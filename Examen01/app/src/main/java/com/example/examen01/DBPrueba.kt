package com.example.examen01

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Planeta::class],
    version = 1
)
abstract class DBPrueba: RoomDatabase() {
    abstract fun daoPlaneta(): DaoPlaneta
}