package com.example.appdishes

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PlatilloViewModel : ViewModel() {

    private val _platillos = mutableStateListOf(

        Platillo(
            1,
            "Pizza Pepperoni",
            "Pizza familiar con abundante pepperoni.",
            12.50,
            "Pizzas",
            "https://images.unsplash.com/photo-1513104890138-7c749659a591"
        ),

        Platillo(
            2,
            "Pizza Hawaiana",
            "Pizza con jamón y piña.",
            13.00,
            "Pizzas",
            "https://images.unsplash.com/photo-1594007654729-407eedc4be65"
        ),

        Platillo(
            3,
            "Pizza Suprema",
            "Pizza con carnes y vegetales.",
            14.50,
            "Pizzas",
            "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38"
        ),

        Platillo(
            4,
            "Hamburguesa Clásica",
            "Hamburguesa de res con queso.",
            8.50,
            "Hamburguesas",
            "https://images.unsplash.com/photo-1568901346375-23c9450c58cd"
        ),

        Platillo(
            5,
            "Hamburguesa BBQ",
            "Hamburguesa con salsa BBQ.",
            9.50,
            "Hamburguesas",
            "https://images.unsplash.com/photo-1550547660-d9450f859349"
        ),

        Platillo(
            6,
            "Hamburguesa Doble",
            "Doble carne y doble queso.",
            11.00,
            "Hamburguesas",
            "https://images.unsplash.com/photo-1571091718767-18b5b1457add"
        )
    )

    val platillos: List<Platillo> = _platillos

    val carrito = mutableStateListOf<Platillo>()

    fun obtenerPlatilloPorId(id:Int):Platillo?{
        return _platillos.find { it.id == id }
    }

    fun agregarAlCarrito(platillo: Platillo){
        carrito.add(platillo)
    }

    fun eliminarDelCarrito(platillo: Platillo){
        carrito.remove(platillo)
    }

    fun vaciarCarrito(){
        carrito.clear()
    }

    fun calcularTotal():Double{
        return carrito.sumOf { it.precio }
    }
}