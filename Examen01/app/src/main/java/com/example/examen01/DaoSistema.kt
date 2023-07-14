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

    @Query("UPDATE sistemas set edad=:edad, descripcion=:descripcion WHERE sistema=:sistema")
    suspend fun actualizarSistema(sistema: String, edad: String, descripcion: String)

    @Query("DELETE FROM sistemas WHERE sistema=:sistema")
    suspend fun borrarSistema(sistema: String)

}