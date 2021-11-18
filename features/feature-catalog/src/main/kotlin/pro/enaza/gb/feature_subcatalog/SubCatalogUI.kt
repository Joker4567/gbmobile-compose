package pro.enaza.gb.feature_subcatalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pro.enaza.gb.gbmobile_theme.theme.DevToTheme
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_model.local.TypeDownload
import pro.enaza.gb.shared_ui.component.CatalogCard
import pro.enaza.gb.shared_ui.component.CategoryHeader
import pro.enaza.gb.shared_ui.component.TopBar

@Composable
fun SubCatalogScreen(
        onCardClick: (GameCard) -> Unit,
        upPress: () -> Unit,
        modifier: Modifier = Modifier,
        categoryName: String,
        gameCardList: List<GameCard>
) {
    Column(Modifier.fillMaxSize()) {
        TopBar(categoryName, upPress)
        CatalogCard(
                onCardClick = onCardClick,
                cardList = gameCardList,
                modifier = modifier
        )
    }
}

@Preview("default")
@Composable
private fun SubCatalogPreview() {
    DevToTheme {
        SubCatalogScreen(
                categoryName = "Заголовок категории",
                gameCardList = listOf(GameCard(
                        1,
                        "Название игры",
                        "", 12,
                        "Экшен, Стратегия",
                        type = TypeDownload.DELETE)),
                modifier = Modifier,
                onCardClick = {},
                upPress = {}
        )
    }
}
