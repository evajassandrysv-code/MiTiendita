
package com.example.mitiendita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// COLORES DE LUNEA SPORT

val rosa = Color(0xFFE8A1BE)
val fondo = Color(0xFFFFF5F9)
val lila = Color(0xFFE9DDF7)
val texto = Color(0xFF513B49)
val blanco = Color(0xFFFFFFFF)

// MODELO DE PRODUCTOS

data class Producto(
    val nombre: String,
    val precio: String,
    val descripcion: String,
    val color: String,
    val imagen: Int
)

// CATALOGO DE PRODUCTOS

val productos = listOf(

    Producto(
        "Top deportivo",
        "$299.00 MXN",
        "Top deportivo con diseño de espalda cruzada. Ideal para entrenar con comodidad y estilo.",
        "Azul celeste",
        R.drawable.top_azul
    ),

    Producto(
        "Short deportivo",
        "$350.00 MXN",
        "Short deportivo de cintura alta, diseñado para brindar comodidad y libertad de movimiento.",
        "Rosa",
        R.drawable.short_rosa
    ),

    Producto(
        "Jumper deportivo",
        "$499.00 MXN",
        "Jumper deportivo de una sola pieza, perfecto para entrenamientos y actividades físicas.",
        "Lila",
        R.drawable.jumper_lila
    ),

    Producto(
        "Chamarra deportiva",
        "$599.00 MXN",
        "Chamarra deportiva de lycra con cierre frontal. Una prenda cómoda y elegante.",
        "Beige",
        R.drawable.chamarra_beige
    ),

    Producto(
        "Leggings deportivos",
        "$450.00 MXN",
        "Leggings deportivos de cintura alta, ideales para entrenar y realizar actividades diarias.",
        "Negro",
        R.drawable.leggings_deportivos
    )
)

// ACTIVIDAD PRINCIPAL

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                LuneaApp()
            }
        }
    }
}

// NAVEGACION

@Composable
fun LuneaApp() {

    var productoSeleccionado by remember {
        mutableStateOf<Producto?>(null)
    }

    if (productoSeleccionado == null) {

        CatalogoScreen(
            onSeleccionar = { producto ->
                productoSeleccionado = producto
            }
        )

    } else {

        DetalleScreen(
            producto = productoSeleccionado!!,
            onRegresar = {
                productoSeleccionado = null
            }
        )
    }
}

// PANTALLA PRINCIPAL

@Composable
fun CatalogoScreen(
    onSeleccionar: (Producto) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .padding(18.dp)
    ) {

        Text(
            text = "LUNÉA SPORT",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = texto
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Move with confidence",
            fontSize = 14.sp,
            color = rosa
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Nuestra colección",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = texto
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Encuentra tu outfit deportivo favorito.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(18.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            items(productos) { producto ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSeleccionar(producto)
                        },
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = blanco
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(
                                id = producto.imagen
                            ),
                            contentDescription = producto.nombre,
                            modifier = Modifier
                                .size(105.dp)
                                .background(
                                    lila,
                                    RoundedCornerShape(12.dp)
                                ),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(
                            modifier = Modifier.width(14.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = producto.nombre,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = texto
                            )

                            Spacer(
                                modifier = Modifier.height(7.dp)
                            )

                            Text(
                                text = producto.precio,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = rosa
                            )

                            Spacer(
                                modifier = Modifier.height(7.dp)
                            )

                            Text(
                                text = "Ver producto",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

// PANTALLA DE DETALLES

@Composable
fun DetalleScreen(
    producto: Producto,
    onRegresar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = "LUNÉA SPORT",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = texto
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Image(
            painter = painterResource(
                id = producto.imagen
            ),
            contentDescription = producto.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .background(
                    blanco,
                    RoundedCornerShape(20.dp)
                ),
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier.height(22.dp)
        )

        Text(
            text = producto.nombre,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = texto
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = producto.precio,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = rosa
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Color: ${producto.color}",
            fontSize = 16.sp,
            color = texto
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = producto.descripcion,
            fontSize = 16.sp,
            color = texto
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Button(
            onClick = onRegresar,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = rosa
            ),
            shape = RoundedCornerShape(12.dp)
        ) {

            Text(
                text = "Regresar a la colección",
                color = blanco,
                fontSize = 15.sp
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}
