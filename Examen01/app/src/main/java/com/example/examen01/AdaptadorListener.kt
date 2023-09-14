package com.example.examen01

interface AdaptadorListener {
    fun onEditItemClick(codigoSistema: String, data: Map<String, Any>)
    fun onDeleteItemClick(codigoistema:String, data: Map<String, Any>)
}