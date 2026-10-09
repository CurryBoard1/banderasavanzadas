package com.example.banderasavanzadas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.banderasavanzadas.ui.theme.BanderasavanzadasTheme

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
                        BanderaSuiza(modifier = Modifier.fillMaxWidth(0.8f))
                    }
                }
            }
        }
    }
}

@Composable
fun BanderaSuiza(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(Color(0xFFD52B1E))
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.2f)
                .fillMaxHeight(0.62f)
                .background(Color.White)
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(0.2f)
                .fillMaxWidth(0.62f)
                .background(Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BanderaSuizaPreview() {
    BanderasavanzadasTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BanderaSuiza(modifier = Modifier.fillMaxWidth(0.8f))
            }
        }
    }
}
