package pro.enaza.gb.feature_catalog.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import pro.enaza.gb.feature_catalog.model.GameCard
import pro.enaza.gb.feature_catalog.model.TypeDownload
import pro.enaza.gb.gbmobile_theme.*
import pro.enaza.gb.gbmobile_theme.component.Circle
import pro.enaza.gb.gbmobile_theme.component.JetsnackDivider
import pro.enaza.gb.gbmobile_theme.component.GbSurface
import pro.enaza.gb.gbmobile_theme.component.SnackImage
import pro.enaza.gb.gbmobile_theme.theme.blue
import pro.enaza.gb.gbmobile_theme.theme.textHelp
import pro.enaza.gb.gbmobile_theme.theme.uiBackground

@Composable
internal fun Cart(
        cardList: List<GameCard>,
        modifier: Modifier = Modifier
) {
    GbSurface(modifier = modifier.fillMaxSize()) {
        Box {
            CartContent(
                    cardList = cardList,
                    modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
private fun CartContent(
        cardList: List<GameCard>,
        modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(cardList) { gameCardDto ->
            CartItem(
                    card = gameCardDto
            )
        }
    }
}

@Composable
private fun CartItem(
        card: GameCard,
        modifier: Modifier = Modifier
) {
    ConstraintLayout(
            modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .background(uiBackground)
                    .padding(horizontal = 18.dp)

    ) {
        val (image, name, categories, state, age, divider, ageText) = createRefs()
        SnackImage(
                imageUrl = card.imageUrl,
                contentDescription = null,
                modifier = Modifier
                        .size(100.dp)
                        .constrainAs(image) {
                            top.linkTo(parent.top, margin = 16.dp)
                            bottom.linkTo(parent.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                        }
        )
        Column(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(image.top)
                    start.linkTo(image.end, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                horizontalAlignment = Alignment.Start
        ) {
            Text(
                    text = card.name,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
            )
        }
        Column(
                modifier = Modifier.constrainAs(categories) {
                    start.linkTo(image.end, margin = 16.dp)
                    top.linkTo(name.bottom, margin = 5.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                horizontalAlignment = Alignment.Start
        ) {
            Text(
                    text = card.tagsCategories,
                    style = MaterialTheme.typography.subtitle2,
                    color = textHelp,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
            )
        }
        Circle(
                modifier = Modifier
                        .constrainAs(age) {
                            top.linkTo(state.top)
                            end.linkTo(parent.end, margin = 16.dp)
                            bottom.linkTo(state.bottom)
                        }
                        .size(22.dp)
        )
        Text(
                text = "${card.age}+",
                fontSize = 11.sp,
                color = White,
                modifier = Modifier.constrainAs(ageText) {
                    bottom.linkTo(age.bottom)
                    start.linkTo(age.start)
                    top.linkTo(age.top)
                    end.linkTo(age.end)
                }
        )
        JetsnackDivider(
                Modifier.constrainAs(divider) {
                    linkTo(start = parent.start, end = parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        StateButton(
                state = card.type,
                modifier = Modifier
                        .constrainAs(state) {
                            bottom.linkTo(image.bottom)
                            start.linkTo(image.end, margin = 16.dp)
                        }
                        .height(24.dp)
        )
    }
}

@Composable
private fun StateButton(state: TypeDownload, modifier: Modifier = Modifier) {
    when (state) {
        TypeDownload.WAIT, TypeDownload.PLAY, TypeDownload.DOWNLOAD, TypeDownload.INSTALL, TypeDownload.ERROR -> {
            Button(
                    onClick = { /* ... */ },
                    contentPadding = PaddingValues(
                            start = 10.dp,
                            end = 10.dp
                    ),
                    modifier = modifier,
                    colors = ButtonDefaults.buttonColors(
                            backgroundColor = blue,
                            contentColor = White
                    )
            ) {
                when (state) {
                    TypeDownload.WAIT, TypeDownload.ERROR ->
                        Text(text = "Скачать", color = White, fontSize = 14.sp)
                    TypeDownload.INSTALL ->
                        Text(text = "Установить", color = White, fontSize = 14.sp)
                    TypeDownload.DOWNLOAD ->
                        Text(text = "Остановить", color = White, fontSize = 14.sp)
                    TypeDownload.PLAY ->
                        Text(text = "Играть", color = White, fontSize = 14.sp)
                }
            }
        }
        TypeDownload.DELETE -> {
            Button(
                    onClick = { /* ... */ },
                    contentPadding = PaddingValues(
                            start = 10.dp,
                            end = 10.dp
                    ),
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

@Preview("default")
@Composable
private fun CardPreview() {
    DevToTheme {
        CartItem(
                card = GameCard(
                        1,
                        "Название игры",
                        "", 12,
                        "Экшен, Стратегия",
                        type = TypeDownload.DELETE),
                modifier = Modifier.padding(16.dp))
    }
}