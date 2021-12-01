package pro.enaza.gb.gbmobile_theme.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
        primary = Color.Black,
        primaryVariant = Color.Black,
        secondary = Color.White,
        onBackground = Color.Black
)

private val LightColorPalette = lightColors(
        primary = Color.Black,
        primaryVariant = Color.Black,
        secondary = Color.White,
        onBackground = Color.Black
)

@Composable
fun DevToTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}
