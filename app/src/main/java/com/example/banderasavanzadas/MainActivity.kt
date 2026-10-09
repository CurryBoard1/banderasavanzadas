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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                BanderaPapuaNuevaGuinea(Modifier.fillMaxWidth().aspectRatio(4f / 3f))
            }
        }
    }
}

@Composable
fun BanderaPapuaNuevaGuinea(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val rojo = Color(0xFFCE1126)
        val dorado = Color(0xFFFCD116)

        // Fondo rojo y triángulo negro diagonal
        drawRect(rojo)
        val negro = Path().apply {
            moveTo(0f, 0f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(negro, Color.Black)

        // Cruz del Sur (5 estrellas blancas en el triángulo negro)
        fun estrella(cx: Float, cy: Float, r: Float): Path {
            val p = Path()
            val rInt = r * 0.4f
            for (i in 0 until 10) {
                val rad = -PI / 2 + i * (PI / 5)
                val radio = if (i % 2 == 0) r else rInt
                val x = cx + radio * cos(rad).toFloat()
                val y = cy + radio * sin(rad).toFloat()
                if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
            }
            p.close()
            return p
        }

        val cx = w * 0.27f
        val cy = h * 0.70f
        val g = h * 0.05f
        drawPath(estrella(cx, cy - h * 0.22f, g), Color.White)
        drawPath(estrella(cx, cy + h * 0.22f, g), Color.White)
        drawPath(estrella(cx - h * 0.17f, cy, g), Color.White)
        drawPath(estrella(cx + h * 0.15f, cy - h * 0.04f, g), Color.White)
        drawPath(estrella(cx + h * 0.08f, cy + h * 0.07f, h * 0.03f), Color.White)

        // Ave del paraíso dorada
        val centroAve = Offset(w * 0.68f, h * 0.28f)
        val e = h * 0.20f
        val puntosAve = listOf(
            0.9f to -0.7f, 0.5f to -0.8f, 0.3f to -0.4f, 0.8f to -0.1f,
            0.2f to 0.0f, 0.5f to 0.7f, 0.0f to 0.3f, -0.4f to 1.0f,
            -0.3f to 0.2f, -0.9f to 0.6f, -0.5f to -0.1f, -0.1f to -0.5f
        )
        val ave = Path().apply {
            puntosAve.forEachIndexed { i, (dx, dy) ->
                val x = centroAve.x + dx * e
                val y = centroAve.y + dy * e
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }
        drawPath(ave, dorado)
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaPapuaNuevaGuineaPreview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BanderaPapuaNuevaGuinea(Modifier.fillMaxWidth().aspectRatio(4f / 3f))
    }
}
