package com.example.banderascompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.cos
import kotlin.math.sin

// Busca donde choca con el borde (arriba o derecha) un rayo que sale de la
// esquina de abajo a la izquierda con cierto angulo (0 = horizontal, 90 = vertical)
private fun puntoEnBorde(w: Float, h: Float, grados: Float): Offset {
    val a = Math.toRadians(grados.toDouble())
    val dx = cos(a).toFloat()
    val dy = sin(a).toFloat()
    // distancia que habria que recorrer para llegar al borde derecho y al de arriba
    val haciaDerecha = if (dx > 0.0001f) w / dx else Float.MAX_VALUE
    val haciaArriba = if (dy > 0.0001f) h / dy else Float.MAX_VALUE
    val t = minOf(haciaDerecha, haciaArriba)
    return Offset(dx * t, h - dy * t)
}

@Composable
fun BanderaSeychelles(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        val azul = Color(0xFF003F87)
        val amarillo = Color(0xFFFCD856)
        val rojo = Color(0xFFD62828)
        val verde = Color(0xFF007A3D)

        val origen = Offset(0f, h)
        val colores = listOf(azul, amarillo, rojo, Color.White, verde)
        // 5 franjas = 6 bordes, repartidos parejo entre 90 y 0 grados
        val angulos = listOf(90f, 72f, 54f, 36f, 18f, 0f)

        for (i in 0 until 5) {
            val a = puntoEnBorde(w, h, angulos[i])
            val b = puntoEnBorde(w, h, angulos[i + 1])
            val franja = Path().apply {
                moveTo(origen.x, origen.y)
                lineTo(a.x, a.y)
                // si un rayo pega arriba y el otro a la derecha hay que pasar por la esquina
                if (a.y < 1f && b.x > w - 1f) lineTo(w, 0f)
                lineTo(b.x, b.y)
                close()
            }
            drawPath(franja, color = colores[i])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaSeychellesPreview() {
    Surface {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BanderaSeychelles(Modifier.fillMaxWidth().aspectRatio(2f))
        }
    }
}

