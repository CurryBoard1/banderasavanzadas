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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                BanderaButan(Modifier.fillMaxWidth().aspectRatio(3f / 2f))
            }
        }
    }
}

@Composable
fun BanderaButan(modifier: Modifier = Modifier) {
    val amarillo = Color(0xFFFFD520)
    val naranja = Color(0xFFFF4E12)

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        drawRect(amarillo)

        val triNaranja = Path().apply {
            moveTo(0f, h)
            lineTo(w, 0f)
            lineTo(w, h)
            close()
        }
        drawPath(triNaranja, naranja)

        val puntos = listOf(
            Offset(w * 0.18f, h * 0.69f),
            Offset(w * 0.25f, h * 0.40f),
            Offset(w * 0.35f, h * 0.52f),
            Offset(w * 0.44f, h * 0.42f),
            Offset(w * 0.54f, h * 0.54f),
            Offset(w * 0.64f, h * 0.45f),
            Offset(w * 0.73f, h * 0.53f),
            Offset(w * 0.82f, h * 0.41f)
        )

        val cuerpo = Path().apply {
            puntos.forEachIndexed { i, p ->
                if (i == 0) moveTo(p.x, p.y) else lineTo(p.x, p.y)
            }
        }
        drawPath(cuerpo, Color.White, style = Stroke(h * 0.08f, cap = StrokeCap.Round, join = StrokeJoin.Miter))

        val pua = Path().apply {
            moveTo(w * 0.82f, h * 0.41f)
            lineTo(w * 0.88f, h * 0.36f)
        }
        drawPath(pua, Color.White, style = Stroke(h * 0.03f, cap = StrokeCap.Round))

        val estrellas = listOf(
            Offset(w * 0.25f, h * 0.40f),
            Offset(w * 0.44f, h * 0.42f),
            Offset(w * 0.64f, h * 0.45f),
            Offset(w * 0.82f, h * 0.41f)
        )

        estrellas.forEach { pos ->
            drawCircle(Color.White, radius = h * 0.055f, center = pos)
            val est = Path().apply {
                val r1 = h * 0.04f
                val r2 = r1 * 0.4f
                for (i in 0 until 10) {
                    val a = -PI / 2 + i * (PI / 5)
                    val r = if (i % 2 == 0) r1 else r2
                    val x = pos.x + r * cos(a).toFloat()
                    val y = pos.y + r * sin(a).toFloat()
                    if (i == 0) moveTo(x, y) else lineTo(x, y)
                }
                close()
            }
            drawPath(est, amarillo)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaButanPreview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BanderaButan(Modifier.fillMaxWidth().aspectRatio(3f / 2f))
    }
}
