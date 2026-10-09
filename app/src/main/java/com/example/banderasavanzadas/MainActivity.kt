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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
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
                        BanderaTurquia(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .aspectRatio(3f / 2f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BanderaTurquia(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val redColor = Color(0xFFE30A17)
        val centerY = height / 2f

        drawRect(color = redColor)

        val outerRadius = height * 0.25f
        val innerRadius = height * 0.20f
        val crescentCenterX = width * 0.35f
        val innerCenterX = crescentCenterX + (height * 0.0625f)

        drawCircle(
            color = Color.White,
            radius = outerRadius,
            center = Offset(crescentCenterX, centerY)
        )
        drawCircle(
            color = redColor,
            radius = innerRadius,
            center = Offset(innerCenterX, centerY)
        )

        val starCenterX = crescentCenterX + (height * 0.375f)
        val starRadius = height * 0.125f

        drawPath(
            path = crearEstrella(
                centerX = starCenterX,
                centerY = centerY,
                radio = starRadius,
                puntas = 5,
                rotacion = 180f
            ),
            color = Color.White
        )
    }
}

private fun crearEstrella(
    centerX: Float,
    centerY: Float,
    radio: Float,
    puntas: Int = 5,
    rotacion: Float = 180f,
    proporcionInterior: Float = 0.382f
): Path {
    val path = Path()
    val totalPuntos = puntas * 2
    val pasoAngulo = 180.0 / puntas

    for (i in 0 until totalPuntos) {
        val r = if (i % 2 == 0) radio else radio * proporcionInterior
        val anguloRad = Math.toRadians(rotacion + i * pasoAngulo)
        val x = centerX + r * cos(anguloRad).toFloat()
        val y = centerY + r * sin(anguloRad).toFloat()

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
fun BanderaTurquiaPreview() {
    BanderasavanzadasTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BanderaTurquia(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .aspectRatio(3f / 2f)
                )
            }
        }
    }
}
