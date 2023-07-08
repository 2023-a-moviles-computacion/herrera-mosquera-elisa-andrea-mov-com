package com.example.examen01

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoPlaneta {

    @Insert
    suspend fun agregarPlaneta(planeta: Planeta)

    @Query("SELECT * FROM planetas")
    suspend fun obtenerPlanetas(): MutableList<Planeta>



    @Query("UPDATE planetas set id=:id WHERE planeta=:planeta")
    suspend fun actualizarPlaneta(planeta: String, id: Int)

    @Query("DELETE FROM planetas WHERE planeta=:planeta")
    suspend fun borrarPlaneta(planeta: String)

}