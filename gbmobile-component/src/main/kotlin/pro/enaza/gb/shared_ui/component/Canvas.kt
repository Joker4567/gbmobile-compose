package pro.enaza.gb.shared_ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import pro.enaza.gb.gbmobile_theme.theme.Black700

@Composable
fun Circle(
        modifier: Modifier,
        color: Color = Black700
) {
    Canvas(
            modifier = modifier,
            onDraw = {
                drawCircle(
                        Brush.linearGradient(
                                colors = listOf(color, color)
                        ),
                        radius = size.width / 2,
                        center = center,
                        style = Stroke(width = size.width * 0.045f)
                )
            }
    )
}