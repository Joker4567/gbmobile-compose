package pro.enaza.gb.gbmobile_theme.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import pro.enaza.gb.gbmobile_theme.DevToTheme
import pro.enaza.gb.gbmobile_theme.R

@Composable
fun SnackImage(
        imageUrl: String,
        contentDescription: String?,
        modifier: Modifier = Modifier,
        elevation: Dp = 0.dp
) {
    GbSurface(
            color = Color.LightGray,
            elevation = elevation,
            shape = RoundedCornerShape(15),
            modifier = modifier
    ) {
        Image(
                painter = rememberImagePainter(
                        data = convertToImgUrl(imageUrl),
                        builder = {
                            crossfade(true)
                            placeholder(drawableResId = R.drawable.placeholder)
                        }
                ),
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
        )
    }
}

@Preview("default")
@Composable
private fun ImagePreview() {
    DevToTheme {
        SnackImage(
                imageUrl = "",
                contentDescription = null,
                modifier = Modifier
                        .size(100.dp)
        )
    }
}

private fun convertToImgUrl(imageUrl: String): String {
    if(imageUrl.startsWith("https", ignoreCase = true))
        return imageUrl
    val paramString =
            "resize/500x500/fullsize/$imageUrl"
    val signString: String = md5Apache(paramString + "VejAmZefhasyop2")
    return "https://resize.enazadev.ru/secure/$signString/$paramString"
}

private fun md5Apache(st: String?): String {
    return String(Hex.encodeHex(DigestUtils.md5(st)))
}