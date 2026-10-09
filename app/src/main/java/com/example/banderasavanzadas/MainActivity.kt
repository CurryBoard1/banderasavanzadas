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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import com.example.banderasavanzadas.ui.theme.BanderasavanzadasTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

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
                        BanderaCuba(
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
fun BanderaCuba(modifier: Modifier = Modifier) {
    val azul = Color(0xFF002E6E)
    val rojo = Color(0xFFCB1428)

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val altoFranja = h / 5f

        drawRect(Color.White)

        // Franjas azules
        for (i in 0..4 step 2) {
            drawRect(
                color = azul,
                topLeft = Offset(0f, i * altoFranja),
                size = Size(w, altoFranja)
            )
        }

        // Triángulo rojo
        val anchoTriangulo = h * 0.866f
        val triangulo = Path().apply {
            moveTo(0f, 0f)
            lineTo(anchoTriangulo, h / 2f)
            lineTo(0f, h)
            close()
        }
        drawPath(triangulo, color = rojo)

        // Estrella blanca
        val cx = anchoTriangulo / 3f
        val cy = h / 2f
        val radioEstrella = h * 0.13f
        drawPath(dibujarEstrella(cx, cy, radioEstrella), color = Color.White)
    }
}

private fun dibujarEstrella(cx: Float, cy: Float, r: Float): Path {
    val path = Path()
    val rInterior = r * 0.4f
    val anguloInicial = -PI / 2

    for (i in 0 until 10) {
        val radioActual = if (i % 2 == 0) r else rInterior
        val angulo = anguloInicial + i * (PI / 5)
        val x = cx + radioActual * cos(angulo).toFloat()
        val y = cy + radioActual * sin(angulo).toFloat()

        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    return path
}

@Preview(showBackground = true)
@Composable
fun BanderaCubaPreview() {
    BanderasavanzadasTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BanderaCuba(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(2f)
            )
        }
    }
}
