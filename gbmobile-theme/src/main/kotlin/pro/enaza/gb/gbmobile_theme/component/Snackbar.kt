package pro.enaza.gb.gbmobile_theme.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pro.enaza.gb.gbmobile_theme.theme.brand
import pro.enaza.gb.gbmobile_theme.theme.textHelp
import pro.enaza.gb.gbmobile_theme.theme.uiBackground

@Composable
fun Snackbar(
        snackbarData: SnackbarData,
        modifier: Modifier = Modifier,
        actionOnNewLine: Boolean = false,
        shape: Shape = MaterialTheme.shapes.small,
        backgroundColor: Color = uiBackground,
        contentColor: Color = textHelp,
        actionColor: Color = brand,
        elevation: Dp = 6.dp
) {
    Snackbar(
            snackbarData = snackbarData,
            modifier = modifier,
            actionOnNewLine = actionOnNewLine,
            shape = shape,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            actionColor = actionColor,
            elevation = elevation
    )
}
