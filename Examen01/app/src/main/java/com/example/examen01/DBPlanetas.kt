package com.example.examen01

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Planeta::class],
    version = 2
)
abstract class DBPlanetas: RoomDatabase() {
    abstract fun daoPlaneta(): DaoPlaneta
}