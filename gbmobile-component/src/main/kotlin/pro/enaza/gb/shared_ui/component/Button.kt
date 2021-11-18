package pro.enaza.gb.shared_ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.enaza.gb.shared_model.local.TypeDownload
import pro.enaza.gb.gbmobile_theme.theme.blue

@Composable
fun StateButton(state: TypeDownload, modifier: Modifier = Modifier, contentPadding: PaddingValues, onClick: () -> Unit) {
    when (state) {
        TypeDownload.WAIT, TypeDownload.PLAY, TypeDownload.DOWNLOAD, TypeDownload.INSTALL, TypeDownload.ERROR -> {
            Button(
                    onClick = { onClick.invoke() },
                    contentPadding = contentPadding,
                    modifier = modifier,
                    colors = ButtonDefaults.buttonColors(
                            backgroundColor = blue,
                            contentColor = Color.White
                    )
            ) {
                when (state) {
                    TypeDownload.WAIT, TypeDownload.ERROR ->
                        Text(text = "Скачать", color = Color.White, fontSize = 14.sp)
                    TypeDownload.INSTALL ->
                        Text(text = "Установить", color = Color.White, fontSize = 14.sp)
                    TypeDownload.DOWNLOAD ->
                        Text(text = "Остановить", color = Color.White, fontSize = 14.sp)
                    TypeDownload.PLAY ->
                        Text(text = "Играть", color = Color.White, fontSize = 14.sp)
                }
            }
        }
        TypeDownload.DELETE -> {
            Button(
                    onClick = { onClick.invoke() },
                    contentPadding = contentPadding,
                    modifier = modifier,
                    colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = pro.enaza.gb.gbmobile_theme.theme.error
                    ),
                    border = BorderStroke(1.dp, pro.enaza.gb.gbmobile_theme.theme.error)
            ) {
                when (state) {
                    TypeDownload.DELETE ->
                        Text(text = "Удалить", color = pro.enaza.gb.gbmobile_theme.theme.error, fontSize = 14.sp)
                }
            }
        }
    }
}
