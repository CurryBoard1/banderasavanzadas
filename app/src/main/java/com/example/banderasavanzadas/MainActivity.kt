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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.example.banderasavanzadas.ui.theme.BanderasavanzadasTheme
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanderasavanzadasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        BanderaIsrael(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .aspectRatio(11f / 8f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BanderaIsrael(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val blueColor = Color(0xFF0038B8)

        drawRect(color = Color.White)

        val margin = height * 0.09f
        val stripeHeight = height * 0.16f

        drawRect(
            color = blueColor,
            topLeft = Offset(0f, margin),
            size = Size(width, stripeHeight)
        )
        drawRect(
            color = blueColor,
            topLeft = Offset(0f, height - margin - stripeHeight),
            size = Size(width, stripeHeight)
        )

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = height * 0.21f
        val strokeWidth = Stroke(width = height * 0.035f)

        drawPath(
            path = crearTrianguloPath(centerX, centerY, radius, -90f),
            color = blueColor,
            style = strokeWidth
        )
        drawPath(
            path = crearTrianguloPath(centerX, centerY, radius, 90f),
            color = blueColor,
            style = strokeWidth
        )
    }
}

private fun crearTrianguloPath(
    centerX: Float,
    centerY: Float,
    radius: Float,
    rotacionGrados: Float
): Path {
    val path = Path()
    for (i in 0..2) {
        val anguloRad = Math.toRadians((rotacionGrados + i * 120).toDouble())
        val x = centerX + radius * cos(anguloRad).toFloat()
        val y = centerY + radius * sin(anguloRad).toFloat()

        if (i == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }
    path.close()
    return path
}

@Preview(showBackground = true)
@Composable
fun BanderaIsraelPreview() {
    BanderasavanzadasTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BanderaIsrael(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .aspectRatio(11f / 8f)
                )
            }
        }
    }
}
