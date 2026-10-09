package com.example.banderasavanzadas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                BanderaNepal()
            }
        }
    }
}

@Composable
fun BanderaNepal(modifier: Modifier = Modifier) {
    val azulBorde = Color(0xFF003893)
    val carmesi = Color(0xFFDC143C)

    Canvas(modifier = modifier.width(240.dp).height(290.dp)) {
        val w = size.width
        val h = size.height

        val a1 = Offset(0f, 0f)
        val a2 = Offset(w * 0.80f, h * 0.40f)
        val a3 = Offset(0f, h * 0.40f)

        val b1 = Offset(0f, h * 0.40f)
        val b2 = Offset(w, h)
        val b3 = Offset(0f, h)

        fun triangulo(p1: Offset, p2: Offset, p3: Offset) = Path().apply {
            moveTo(p1.x, p1.y)
            lineTo(p2.x, p2.y)
            lineTo(p3.x, p3.y)
            close()
        }

        fun incentro(p1: Offset, p2: Offset, p3: Offset): Offset {
            val l1 = (p2 - p3).getDistance()
            val l2 = (p1 - p3).getDistance()
            val l3 = (p1 - p2).getDistance()
            val s = l1 + l2 + l3
            return Offset((l1 * p1.x + l2 * p2.x + l3 * p3.x) / s, (l1 * p1.y + l2 * p2.y + l3 * p3.y) / s)
        }

        val arriba = triangulo(a1, a2, a3)
        val abajo = triangulo(b1, b2, b3)

        drawPath(arriba, azulBorde)
        drawPath(abajo, azulBorde)

        scale(0.72f, pivot = incentro(a1, a2, a3)) {
            drawPath(arriba, carmesi)
        }
        scale(0.72f, pivot = incentro(b1, b2, b3)) {
            drawPath(abajo, carmesi)
        }

        // Luna
        val luna = Offset(w * 0.25f, h * 0.27f)
        drawCircle(Color.White, radius = h * 0.06f, center = luna)
        drawCircle(carmesi, radius = h * 0.052f, center = Offset(luna.x, luna.y - h * 0.025f))

        // Sol de 12 puntas
        val sol = Path().apply {
            val cx = w * 0.30f
            val cy = h * 0.80f
            val rExt = h * 0.085f
            val rInt = rExt * 0.7f
            for (i in 0 until 24) {
                val a = -PI / 2 + i * (PI / 12)
                val r = if (i % 2 == 0) rExt else rInt
                val x = cx + r * cos(a).toFloat()
                val y = cy + r * sin(a).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }
        drawPath(sol, Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaNepalPreview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BanderaNepal()
    }
}
