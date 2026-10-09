package com.example.banderasavanzadas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.hypot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                BanderaReinoUnido(Modifier.fillMaxWidth().aspectRatio(2f))
            }
        }
    }
}

@Composable
fun BanderaReinoUnido(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val azul = Color(0xFF012169)
        val rojo = Color(0xFFC8102E)
        val centro = Offset(w / 2f, h / 2f)

        clipRect {
            // Fondo azul
            drawRect(azul)

            // Diagonales blancas
            val grosorBlanca = h * 0.20f
            drawLine(Color.White, Offset(0f, 0f), Offset(w, h), grosorBlanca)
            drawLine(Color.White, Offset(w, 0f), Offset(0f, h), grosorBlanca)

            // Diagonales rojas (asimétricas)
            val largo = hypot(w, h)
            val corrimiento = h * 0.033f
            val grosorRoja = h * 0.067f
            val p1 = Offset(-h / largo, w / largo) * corrimiento
            val p2 = Offset(h / largo, w / largo) * corrimiento

            drawLine(rojo, centro + p1, Offset(0f, 0f) + p1, grosorRoja)
            drawLine(rojo, centro - p1, Offset(w, h) - p1, grosorRoja)
            drawLine(rojo, centro - p2, Offset(w, 0f) - p2, grosorRoja)
            drawLine(rojo, centro + p2, Offset(0f, h) + p2, grosorRoja)

            // Cruz blanca
            val anchoBlanca = h * 0.333f
            drawRect(
                Color.White,
                topLeft = Offset(0f, centro.y - anchoBlanca / 2f),
                size = Size(w, anchoBlanca)
            )
            drawRect(
                Color.White,
                topLeft = Offset(centro.x - anchoBlanca / 2f, 0f),
                size = Size(anchoBlanca, h)
            )

            // Cruz roja
            val anchoRoja = h * 0.20f
            drawRect(
                rojo,
                topLeft = Offset(0f, centro.y - anchoRoja / 2f),
                size = Size(w, anchoRoja)
            )
            drawRect(
                rojo,
                topLeft = Offset(centro.x - anchoRoja / 2f, 0f),
                size = Size(anchoRoja, h)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaReinoUnidoPreview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BanderaReinoUnido(Modifier.fillMaxWidth().aspectRatio(2f))
    }
}
