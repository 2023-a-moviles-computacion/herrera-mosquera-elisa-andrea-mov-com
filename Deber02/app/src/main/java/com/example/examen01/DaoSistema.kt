package com.example.examen01

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoSistema {
    //Metodos del CRUD

    @Query("SELECT * FROM sistemas")
    suspend fun obtenerSistemas(): MutableList<Sistema>

    @Insert
    suspend fun agregarSistema(sistema: Sistema)

    @Query("UPDATE sistemas set sistema=:sistema,edad=:edad, galaxia=:galaxia, descripcion=:descripcion WHERE codigoSistema=:codigoSistema")
    suspend fun actualizarSistema(codigoSistema: Int,sistema: String, edad: String, galaxia: String, descripcion: String)

    @Query("DELETE FROM sistemas WHERE codigoSistema=:codigoSistema")
    suspend fun borrarSistema(codigoSistema: Int)

    @Query("SELECT codigoSistema FROM sistemas LIMIT 1")
    suspend fun obtenerCodigoActual(): Int?

}