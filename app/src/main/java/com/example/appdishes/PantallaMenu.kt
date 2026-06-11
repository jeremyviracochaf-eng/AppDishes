package com.example.appdishes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMenu(
    navController: NavController,
    viewModel: PlatilloViewModel,
    nombre: String
) {

    var filtroSeleccionado by remember {
        mutableStateOf("Todos")
    }

    val listaFiltrada = when (filtroSeleccionado) {

        "Pizzas" ->
            viewModel.platillos.filter {
                it.categoria == "Pizzas"
            }

        "Hamburguesas" ->
            viewModel.platillos.filter {
                it.categoria == "Hamburguesas"
            }

        else ->
            viewModel.platillos
    }

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("Hola, $nombre")
                }
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("carrito")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrito"
                )
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(
                text = "Menú BiteBox",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = {
                        filtroSeleccionado = "Todos"
                    }
                ) {
                    Text("Todos")
                }

                Button(
                    onClick = {
                        filtroSeleccionado = "Pizzas"
                    }
                ) {
                    Text("Pizzas")
                }

                Button(
                    onClick = {
                        filtroSeleccionado = "Hamburguesas"
                    }
                ) {
                    Text("Hamburguesas")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(listaFiltrada) { platillo ->

                    ItemPlatillo(
                        platillo = platillo,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun ItemPlatillo(
    platillo: Platillo,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

                navController.navigate(
                    "detalles/${platillo.id}"
                )
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp)
        ) {

            AsyncImage(
                model = platillo.urlImagen,
                contentDescription = platillo.nombre,
                modifier = Modifier
                    .size(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {

                Text(
                    text = platillo.nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Categoría: ${platillo.categoria}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$${platillo.precio}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}