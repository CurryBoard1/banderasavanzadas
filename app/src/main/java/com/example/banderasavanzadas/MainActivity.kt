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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                BanderaSudafrica(Modifier.fillMaxWidth().aspectRatio(3f / 2f))
            }
        }
    }
}

@Composable
fun BanderaSudafrica(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cy = h / 2f

        val rojo = Color(0xFFDE3831)
        val azul = Color(0xFF002395)
        val verde = Color(0xFF007A4D)
        val dorado = Color(0xFFFFB612)

        val arriba = Offset(0f, 0f)
        val abajo = Offset(0f, h)
        val union = Offset(h * 0.54f, cy)
        val derecha = Offset(w, cy)

        clipRect {
            // Fondo superior rojo e inferior azul
            drawRect(color = rojo, size = Size(w, cy))
            drawRect(color = azul, topLeft = Offset(0f, cy), size = Size(w, h - cy))

            // Y blanca
            val grosorBlanco = h * 0.30f
            drawLine(Color.White, arriba, union, grosorBlanco, cap = StrokeCap.Round)
            drawLine(Color.White, abajo, union, grosorBlanco, cap = StrokeCap.Round)
            drawLine(Color.White, union, derecha, grosorBlanco, cap = StrokeCap.Round)

            // Y verde
            val grosorVerde = h * 0.20f
            drawLine(verde, arriba, union, grosorVerde, cap = StrokeCap.Round)
            drawLine(verde, abajo, union, grosorVerde, cap = StrokeCap.Round)
            drawLine(verde, union, derecha, grosorVerde, cap = StrokeCap.Round)

            // Triángulo dorado
            val trianguloDorado = Path().apply {
                moveTo(0f, h * 0.136f)
                lineTo(h * 0.393f, cy)
                lineTo(0f, h * 0.864f)
                close()
            }
            drawPath(trianguloDorado, color = dorado)

            // Triángulo negro
            val trianguloNegro = Path().apply {
                moveTo(0f, h * 0.204f)
                lineTo(h * 0.320f, cy)
                lineTo(0f, h * 0.796f)
                close()
            }
            drawPath(trianguloNegro, color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaSudafricaPreview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BanderaSudafrica(Modifier.fillMaxWidth().aspectRatio(3f / 2f))
    }
}
