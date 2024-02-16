package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                DrawCircle()
            }
        }
    }
}

@Composable
fun DrawCircle() {
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawCircle(Color.Magenta)
            }
    )
}

@Composable
@Preview
fun DrawCirclePreview() {
    DrawCircle()
}
