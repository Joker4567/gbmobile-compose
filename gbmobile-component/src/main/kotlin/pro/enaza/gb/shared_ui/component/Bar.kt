package pro.enaza.gb.shared_ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.enaza.gb.gbmobile_theme.theme.Black200
import pro.enaza.gb.gbmobile_theme.theme.Black900
import pro.enaza.gb.gbmobile_theme.theme.lightBlue
import pro.enaza.gb.shared_model.local.CatalogCategory
import pro.enaza.gb.shared_model.local.GameCard

@Composable
fun TopBar(title: String, onBackIconClick: () -> Unit) {
    Column {
        TopAppBar(
                backgroundColor = Black900,
                navigationIcon = {
                    IconButton(onClick = {
                        onBackIconClick()
                    }) {
                        Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                tint = Black200,
                                contentDescription = "Назад"
                        )
                    }
                },
                actions = {

                },
                title = {
                    Text(
                            text = title,
                            color = Color.White,
                            style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center)
                    )
                }
        )
    }
}

@Composable
fun CategoryHeader(title: String, onSubCatalog: (CatalogCategory) -> Unit, source: List<GameCard>) {
    Box(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp, top = 12.dp, bottom = 8.dp)) {
        Text(
                text = title.toUpperCase(),
                modifier = Modifier
                        .align(Alignment.CenterStart),
                color = White,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1
        )
        Text(
                text = "смотреть все",
                modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { onSubCatalog.invoke(
                                CatalogCategory(title, source)
                        ) },
                color = lightBlue,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 1
        )
    }
}