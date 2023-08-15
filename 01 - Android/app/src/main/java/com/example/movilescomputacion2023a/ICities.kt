package com.example.movilescomputacion2023a

class ICities(
    public var name: String?,
    public var state: String?,
    public var country: String?,
    public var capital: String?,
    public var population: String?,
    public var regiones: List<String>?
) {
    override fun toString(): String {
        return "${name} - ${country}"
    }
}