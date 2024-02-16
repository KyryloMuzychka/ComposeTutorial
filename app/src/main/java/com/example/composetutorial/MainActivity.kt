package com.example.composetutorial

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {

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
fun DrawRectangle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2F
        drawRect(
            color = Color.Green,
            size = canvasQuadrantSize
        )
    }
}

@Composable
fun DrawLine() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasSizeWidth = size.width
        val canvasSizeHeight = size.height
        drawLine(
            start = Offset(x = canvasSizeWidth, y = 0f),
            end = Offset(x = 0f, y = canvasSizeHeight),
            color = Color.Blue
        )
    }
}

@Composable
fun DrawWithScale() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        scale(scaleX = 4f, scaleY = 8f) {
            drawCircle(
                color = Color.Cyan,
                radius = 20.dp.toPx()
            )
        }
    }
}

@Composable
fun DrawWithTranslate() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(left = 80f, top = -600f) {
            drawCircle(color = Color.Blue, radius = 200.dp.toPx())
        }
    }
}

@Composable
fun DrawWithRotate() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        rotate(degrees = 45f) {
            drawRect(
                color = Color.Gray,
                size = size / 3f,
                topLeft = Offset(x = size.width / 3f, y = size.height / 3f)
            )
        }
    }
}

@Composable
fun DrawWithInset() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2f
        inset(horizontal = 150f, vertical = 100f) {
            drawRect(
                color = Color.Yellow,
                size = canvasQuadrantSize
            )
        }
    }
}

@Composable
fun DrawWithMultipleTransformations() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        withTransform({
            translate(left = size.width / 5f)
            rotate(degrees = 45f)
        }) {
            drawRect(
                color = Color.Red,
                size = size / 3f,
                topLeft = Offset(x = size.width / 3F, y = size.height / 3f)
            )
        }
    }
}

@Composable
fun DrawText() {
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawText(textMeasurer, "Hello")
    }
}

@Composable
fun DrawTextWithBackground() {
    val text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    val textMeasurer = rememberTextMeasurer()
    Spacer(
        modifier = Modifier
            .drawWithCache {
                val measuredText =
                    textMeasurer.measure(
                        AnnotatedString(text),
                        style = TextStyle(fontSize = 18.sp),
                        constraints = Constraints.fixed(
                            width = (size.width * 1f / 3f).toInt(),
                            height = (size.height * 1f / 3f).toInt()
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                onDrawBehind {
                    drawRect(
                        color = Color(0xffe06996),
                        size = measuredText.size.toSize()
                    )
                    drawText(
                        measuredText
                    )
                }
            }
            .fillMaxSize()
    )
}

@Composable
fun DrawImage() {
    var imageGirl = ImageBitmap.imageResource(R.drawable.profile_image)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .scale(0.2f)
            .offset(x = (-400).dp, y = (-500).dp)
    ) {
        drawImage(imageGirl)
    }
}

@Composable
fun DrawShapeCircle() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        onDraw = {
            drawCircle(purpleColor)
        }
    )
}

@Composable
fun DrawShapeRect() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        onDraw = {
            drawRect(purpleColor)
        }
    )
}

@Composable
fun DrawShapeRoundRect() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val rect = Rect(0f, 0f, size.width, size.height)
        val cornerRadius = 100f
        drawRoundRect(color = purpleColor, topLeft = Offset(rect.left, rect.top), size = Size(rect.width, rect.height), cornerRadius = CornerRadius(cornerRadius,cornerRadius))
    }
}

@Composable
fun DrawShapeLine() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        onDraw = {
            drawLine(
                color = purpleColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, size.height)
            )
        }
    )
}

@Composable
fun DrawShapeOval() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        onDraw = {
            drawOval(purpleColor)
        }
    )
}

@Composable
fun DrawShapeArc() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drawArc(
            color = purpleColor,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = true,
            size = Size(size.width * 0.8f, size.height * 0.5f)
        )
    }
}

@Composable
fun DrawShapePoints() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val pointRadius = 8f
        val points = listOf(
            Offset(50f, 50f),
            Offset(100f, 150f),
            Offset(200f, 100f),
            Offset(300f, 200f),
            Offset(600f, 365f)
        )
        points.forEach { point ->
            drawCircle(color = purpleColor, radius = pointRadius, center = point)
        }
    }
}

@Composable
fun DrawTrianglePath() {
    Spacer(
        modifier = Modifier
            .drawWithCache {
                val path = Path()
                path.moveTo(0f, 0f)
                path.lineTo(size.width / 2f, size.height / 2f)
                path.lineTo(size.width, 0f)
                path.close()
                onDrawBehind {
                    drawPath(path, Color.Magenta, style = Stroke(width = 10f))
                }
            }
            .fillMaxSize()
    )
}

@Composable
fun AccessToCanvas() {
    val drawable = ShapeDrawable(OvalShape())
    Spacer(
        modifier = Modifier
            .drawWithContent {
                drawIntoCanvas { canvas ->
                    drawable.setBounds(0, 0, size.width.toInt(), size.height.toInt())
                    drawable.draw(canvas.nativeCanvas)
                }
            }
            .fillMaxSize()
    )
}

@Composable
@Preview(showBackground = true)
fun DrawCirclePreview() {
    DrawCircle()
}

@Composable
@Preview(showBackground = true)
fun DrawRectanglePreview() {
    DrawRectangle()
}

@Composable
@Preview(showBackground = true)
fun DrawLinePreview() {
    DrawLine()
}

@Composable
@Preview(showBackground = true)
fun DrawWithScalePreview() {
    DrawWithScale()
}

@Composable
@Preview(showBackground = true)
fun DrawWithTranslatePreview() {
    DrawWithTranslate()
}

@Composable
@Preview(showBackground = true)
fun DrawWithRotatePreview() {
    DrawWithRotate()
}

@Composable
@Preview(showBackground = true)
fun DrawWithInsetPreview() {
    DrawWithInset()
}

@Composable
@Preview(showBackground = true)
fun DrawWithMultipleTransformationsPreview() {
    DrawWithMultipleTransformations()
}

@Composable
@Preview(showBackground = true)
fun DrawTextPreview() {
    DrawText()
}

@Composable
@Preview(showBackground = true)
fun DrawTextWithBackgroundPreview() {
    DrawTextWithBackground()
}

@Composable
@Preview(showBackground = true)
fun DrawImagePreview() {
    DrawImage()
}

@Composable
@Preview(showBackground = true)
fun DrawShapeCirclePreview() {
    DrawShapeCircle()
}

@Composable
@Preview(showBackground = true)
fun DrawShapeRectPreview() {
    DrawShapeRect()
}

@Composable
@Preview(showBackground = true)
fun DrawShapeRoundRectPreview() {
    DrawShapeRoundRect()
}

@Composable
@Preview(showBackground = true)
fun DrawShapeLinePreview() {
    DrawShapeLine()
}

@Composable
@Preview(showBackground = true)
fun DrawShapeOvalPreview() {
    DrawShapeOval()
}

@Composable
@Preview(showBackground = true)
fun DrawShapeAcrPreview() {
    DrawShapeArc()
}

@Composable
@Preview(showBackground = true)
fun DrawShapePointsPreview() {
    DrawShapePoints()
}

@Composable
@Preview(showBackground = true)
fun DrawTrianglePathPReview() {
    DrawTrianglePath()
}

@Composable
@Preview(showBackground = true)
fun AccessToCanvasPreview() {
    AccessToCanvas()
}