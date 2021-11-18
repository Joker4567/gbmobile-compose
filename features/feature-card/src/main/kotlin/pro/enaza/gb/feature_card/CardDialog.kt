package pro.enaza.gb.feature_card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_model.local.TypeDownload
import pro.enaza.gb.gbmobile_theme.theme.DevToTheme
import pro.enaza.gb.shared_ui.component.Circle
import pro.enaza.gb.shared_ui.component.StateButton
import pro.enaza.gb.gbmobile_theme.theme.Black700
import pro.enaza.gb.gbmobile_theme.theme.blue
import pro.enaza.gb.gbmobile_theme.theme.uiBackground
import pro.enaza.gb.shared_ui.component.SnackImage

@Composable
fun CardDialog(card: GameCard, onClose: () -> Unit, onRemove: () -> Unit, onAction: () -> Unit) {
    Dialog(onDismissRequest = onClose) {
        containerDialog(card, onClose, onRemove, onAction)
    }
}

@Composable
private fun containerDialog(card: GameCard, onClose: () -> Unit, onRemove: () -> Unit, onAction: () -> Unit) {
    Surface(
            modifier = Modifier
                    .fillMaxWidth(1F)
                    .height(250.dp),
            shape = RoundedCornerShape(14.dp),
            color = Color.LightGray
    ) {
        ConstraintLayout(
                modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .background(uiBackground)
                        .padding(horizontal = 18.dp)

        ) {
            val (image, title, state, age, remove, ageText) = createRefs()
            SnackImage(
                    imageUrl = card.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                            .size(125.dp)
                            .constrainAs(image) {
                                top.linkTo(parent.top, margin = 16.dp)
                                start.linkTo(parent.start, margin = 16.dp)
                                end.linkTo(parent.end, margin = 16.dp)
                            }
            )
            Column(
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(image.bottom, margin = 12.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        width = Dimension.fillToConstraints
                    },
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                        text = card.name,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                )
                Text(
                        text = card.tagsCategories,
                        style = MaterialTheme.typography.caption,
                        color = Black700,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 2.dp)
                )
            }
            IconButton(
                    onClick = { onClose.invoke() },
                    modifier = Modifier
                            .constrainAs(remove) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end, margin = 10.dp)
                            }
            ) {
                Icon(
                        imageVector = Icons.Filled.Close,
                        tint = White,
                        contentDescription = "Удалить"
                )
            }
            containerButtonCard(
                    modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(state) {
                                top.linkTo(title.bottom, margin = 15.dp)
                                start.linkTo(parent.start, margin = 10.dp)
                                end.linkTo(parent.end, margin = 10.dp)
                            },
                    card, onRemove, onAction)
        }
    }
}

@Composable
private fun containerButtonCard(modifier: Modifier = Modifier, card: GameCard, onRemove: () -> Unit, onAction: () -> Unit) {
    ConstraintLayout(
            modifier = modifier

    ) {
        val (state, remove, age, ageText) = createRefs()
        when (card.type) {
            TypeDownload.PLAY, TypeDownload.INSTALL -> {
                StateButton(
                        state = TypeDownload.DELETE,
                        modifier = Modifier
                                .constrainAs(remove) {
                                    bottom.linkTo(parent.bottom, margin = 10.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(state.start, margin = 5.dp)
                                },
                        contentPadding = PaddingValues(
                                start = 20.dp,
                                end = 20.dp
                        ),
                        onClick = onRemove
                )
                StateButton(
                        state = card.type,
                        modifier = Modifier
                                .constrainAs(state) {
                                    bottom.linkTo(parent.bottom, margin = 10.dp)
                                    start.linkTo(remove.end, margin = 5.dp)
                                    end.linkTo(age.start, margin = 10.dp)
                                    width = Dimension.fillToConstraints
                                },
                        contentPadding = PaddingValues(
                                start = 20.dp,
                                end = 20.dp
                        ),
                        onClick = onAction
                )
            }
            TypeDownload.WAIT, TypeDownload.DOWNLOAD, TypeDownload.ERROR -> {
                StateButton(
                        state = card.type,
                        modifier = Modifier
                                .constrainAs(state) {
                                    bottom.linkTo(parent.bottom, margin = 10.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                        contentPadding = PaddingValues(
                                start = 20.dp,
                                end = 20.dp
                        ),
                        onClick = onAction
                )
            }
        }
        Circle(
                modifier = Modifier
                        .constrainAs(age) {
                            top.linkTo(state.top)
                            end.linkTo(parent.end, margin = 16.dp)
                            bottom.linkTo(state.bottom)
                            height = Dimension.fillToConstraints
                        }.size(30.dp),
                color = blue
        )
        Text(
                text = "${card.age}+",
                fontSize = 13.sp,
                color = White,
                modifier = Modifier.constrainAs(ageText) {
                    bottom.linkTo(age.bottom)
                    start.linkTo(age.start)
                    top.linkTo(age.top)
                    end.linkTo(age.end)
                }
        )
    }

}

@Preview("default", showBackground = false)
@Composable
private fun CardPreview() {
    DevToTheme {
        containerDialog(
                GameCard(
                        1,
                        "Железный десант",
                        "",
                        7,
                        "Strategy, tower Deference",
                        TypeDownload.DOWNLOAD),
                {},{},{})
    }
}