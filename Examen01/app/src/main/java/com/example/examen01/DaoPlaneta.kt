package com.example.examen01

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoPlaneta {

    @Query("SELECT * FROM planetas WHERE codigoSistema =:codigoSistema")
    suspend fun obtenerPlanetaPorCodigoPlaneta(codigoSistema: Int): MutableList<Planeta>

    @Insert
    suspend fun agregarPlaneta(planeta: Planeta)

    @Query("UPDATE planetas set edadPlaneta=:edadPlaneta, descripcionP=:descripcionP,codigoSistema=:codigoSistema WHERE planeta=:planeta")
    suspend fun actualizarPlaneta(planeta: String, edadPlaneta: String, descripcionP: String, codigoSistema: Int)

    @Query("DELETE FROM planetas WHERE planeta=:planeta")
    suspend fun borrarPlaneta(planeta: String)
}