package com.example.examen01

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoPlaneta {

    @Query("SELECT * FROM planetas")
    suspend fun obtenerPlanetas(): MutableList<Planeta>

    @Insert
    suspend fun agregarPlaneta(planeta: Planeta)

    @Query("UPDATE planetas set edadPlaneta=:edadPlaneta, descripcionPlaneta=:descripcionPlaneta WHERE planeta=:planeta")
    suspend fun actualizarPlaneta(planeta: String, edadPlaneta: String, descripcionPlaneta: String)

    @Query("DELETE FROM planetas WHERE planeta=:planeta")
    suspend fun borrarPlaneta(planeta: String)
}