package pro.enaza.gb.shared_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_model.local.TypeDownload
import pro.enaza.gb.gbmobile_theme.*
import pro.enaza.gb.gbmobile_theme.theme.textHelp
import pro.enaza.gb.gbmobile_theme.theme.uiBackground

@Composable
fun Cart(
        onCardClick: (GameCard) -> Unit,
        cardList: List<GameCard>,
        modifier: Modifier = Modifier
) {
    GbSurface(modifier = modifier.fillMaxSize()) {
        Box {
            CartContent(
                    cardList = cardList,
                    onCardClick = onCardClick,
                    modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
private fun CartContent(
        cardList: List<GameCard>,
        onCardClick: (GameCard) -> Unit,
        modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(cardList) { gameCardDto ->
            CartItem(
                    card = gameCardDto,
                    onCardClick = onCardClick
            )
        }
    }
}

@Composable
private fun CartItem(
        card: GameCard,
        onCardClick: (GameCard) -> Unit,
        modifier: Modifier = Modifier
) {
    ConstraintLayout(
            modifier = modifier
                    .fillMaxWidth()
                    .clickable { onCardClick.invoke(card) }
                    .background(uiBackground)
                    .padding(horizontal = 18.dp)

    ) {
        val (image, name, state, age, divider, ageText) = createRefs()
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
                    overflow = TextOverflow.Ellipsis
            )
            Text(
                    text = card.tagsCategories,
                    style = MaterialTheme.typography.subtitle2,
                    color = textHelp,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 5.dp)
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
                        .height(24.dp),
                contentPadding = PaddingValues(
                        start = 10.dp,
                        end = 10.dp
                ),
                onClick = { /* onAction */ }
        )
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
                modifier = Modifier.padding(16.dp),
                onCardClick = {}
        )
    }
}