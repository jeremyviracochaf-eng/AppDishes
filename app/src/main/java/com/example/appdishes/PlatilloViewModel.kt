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
            ""
        ),

        Platillo(
            2,
            "Pizza Hawaiana",
            "Pizza con jamón y piña.",
            13.00,
            "Pizzas",
            ""
        ),

        Platillo(
            3,
            "Pizza Suprema",
            "Pizza con carnes y vegetales.",
            14.50,
            "Pizzas",
            ""
        ),

        Platillo(
            4,
            "Hamburguesa Clásica",
            "Hamburguesa de res con queso.",
            8.50,
            "Hamburguesas",
            ""
        ),

        Platillo(
            5,
            "Hamburguesa BBQ",
            "Hamburguesa con salsa BBQ.",
            9.50,
            "Hamburguesas",
            ""
        ),

        Platillo(
            6,
            "Hamburguesa Doble",
            "Doble carne y doble queso.",
            11.00,
            "Hamburguesas",
            ""
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

    fun vaciarCarrito(){
        carrito.clear()
    }

    fun calcularTotal():Double{
        return carrito.sumOf { it.precio }
    }
}