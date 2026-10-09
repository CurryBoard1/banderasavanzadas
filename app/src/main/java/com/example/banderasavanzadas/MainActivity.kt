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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                BanderaKiribati(Modifier.fillMaxWidth().aspectRatio(3f / 2f))
            }
        }
    }
}

@Composable
fun BanderaKiribati(modifier: Modifier = Modifier) {
    val rojo = Color(0xFFCE1126)
    val azul = Color(0xFF003893)
    val dorado = Color(0xFFFCD116)

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cy = h / 2f
        val cx = w / 2f

        clipRect {
            drawRect(rojo, size = Size(w, cy))
            drawRect(azul, topLeft = Offset(0f, cy), size = Size(w, h - cy))

            val stripeH = cy / 6f
            val amp = stripeH * 0.35f
            val numPicos = 10

            for (k in 0..2) {
                val yBase = cy + (k * 2) * stripeH
                val ola = Path()

                for (i in 0..numPicos) {
                    val x = w * i / numPicos
                    val dy = if (i % 2 == 0) 0f else amp
                    val y = yBase + dy
                    if (i == 0) ola.moveTo(x, y) else ola.lineTo(x, y)
                }

                for (i in numPicos downTo 0) {
                    val x = w * i / numPicos
                    val dy = if (i % 2 == 0) 0f else amp
                    val y = yBase + stripeH + dy
                    ola.lineTo(x, y)
                }

                ola.close()
                drawPath(ola, Color.White)
            }

            val rSol = h * 0.14f
            drawArc(
                color = dorado,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(cx - rSol, cy - rSol),
                size = Size(rSol * 2f, rSol * 2f)
            )

            val numRayos = 15
            val r1 = rSol * 1.15f
            val r2 = rSol * 1.65f

            for (i in 0 until numRayos) {
                val ang = PI - (i * (PI / (numRayos - 1))) + (if (i % 2 == 0) 0.02 else -0.02)
                val x1 = cx + r1 * cos(ang).toFloat()
                val y1 = cy - r1 * sin(ang).toFloat()
                val x2 = cx + r2 * cos(ang).toFloat()
                val y2 = cy - r2 * sin(ang).toFloat()

                drawLine(
                    color = dorado,
                    start = Offset(x1, y1),
                    end = Offset(x2, y2),
                    strokeWidth = h * 0.028f,
                    cap = StrokeCap.Butt
                )
            }

            val aveY = h * 0.18f
            val ave = Path().apply {
                moveTo(cx, aveY - h * 0.07f)
                quadraticTo(cx - w * 0.08f, aveY - h * 0.05f, cx - w * 0.18f, aveY + h * 0.02f)
                quadraticTo(cx - w * 0.08f, aveY, cx, aveY - h * 0.02f)
                quadraticTo(cx + w * 0.08f, aveY, cx + w * 0.18f, aveY + h * 0.02f)
                quadraticTo(cx + w * 0.08f, aveY - h * 0.05f, cx, aveY - h * 0.07f)
                close()
            }
            drawPath(ave, dorado)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaKiribatiPreview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BanderaKiribati(Modifier.fillMaxWidth().aspectRatio(3f / 2f))
    }
}
