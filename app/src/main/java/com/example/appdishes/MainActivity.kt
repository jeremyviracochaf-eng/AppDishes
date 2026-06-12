package com.example.appdishes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appdishes.ui.theme.AppDishesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AppDishesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val biteBoxViewModel: PlatilloViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {

            PantallaLogin(navController)
        }

        composable("menu/{nombre}") {

            val nombre =
                it.arguments?.getString("nombre") ?: ""

            PantallaMenu(
                navController,
                biteBoxViewModel,
                nombre
            )
        }

        composable(
            route = "detalles/{platilloId}",
            arguments = listOf(
                navArgument("platilloId") {
                    type = NavType.IntType
                }
            )
        ) {

            val id =
                it.arguments?.getInt("platilloId") ?: 0

            PantallaDetalles(
                navController,
                biteBoxViewModel,
                id
            )
        }

        composable("carrito") {

            PantallaCarrito(
                navController,
                biteBoxViewModel
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DishesAppPreview() {
    AppDishesTheme{
        AppNavigation()
    }
}