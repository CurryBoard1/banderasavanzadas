package com.example.banderasavanzadas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import com.example.banderasavanzadas.ui.theme.BanderasavanzadasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanderasavanzadasTheme {
                Scaffold { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        BanderaSeychelles(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .aspectRatio(2f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BanderaSeychelles(modifier: Modifier = Modifier) {
    val azul = Color(0xFF003F87)
    val amarillo = Color(0xFFFCD856)
    val rojo = Color(0xFFD62828)
    val blanco = Color.White
    val verde = Color(0xFF007A3D)

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Franja 1: Azul
        val pathAzul = Path().apply {
            moveTo(0f, h)
            lineTo(0f, 0f)
            lineTo(w / 3f, 0f)
            close()
        }
        drawPath(pathAzul, color = azul)

        // Franja 2: Amarilla
        val pathAmarillo = Path().apply {
            moveTo(0f, h)
            lineTo(w / 3f, 0f)
            lineTo(2f * w / 3f, 0f)
            close()
        }
        drawPath(pathAmarillo, color = amarillo)

        // Franja 3: Roja
        val pathRojo = Path().apply {
            moveTo(0f, h)
            lineTo(2f * w / 3f, 0f)
            lineTo(w, 0f)
            lineTo(w, h / 3f)
            close()
        }
        drawPath(pathRojo, color = rojo)

        // Franja 4: Blanca
        val pathBlanco = Path().apply {
            moveTo(0f, h)
            lineTo(w, h / 3f)
            lineTo(w, 2f * h / 3f)
            close()
        }
        drawPath(pathBlanco, color = blanco)

        // Franja 5: Verde
        val pathVerde = Path().apply {
            moveTo(0f, h)
            lineTo(w, 2f * h / 3f)
            lineTo(w, h)
            close()
        }
        drawPath(pathVerde, color = verde)
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaSeychellesPreview() {
    BanderasavanzadasTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BanderaSeychelles(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(2f)
            )
        }
    }
}
