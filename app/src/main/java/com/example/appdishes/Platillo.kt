package com.example.appdishes

data class Platillo(
    val id:Int,
    val nombre:String,
    val descripcion:String,
    val precio:Double,
    val categoria:String,
    val urlImagen:String
)