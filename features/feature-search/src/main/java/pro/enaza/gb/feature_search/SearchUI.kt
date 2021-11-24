package pro.enaza.gb.feature_search

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import pro.enaza.gb.feature_search.component.SearchContent
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_model.local.TypeDownload

@Composable
fun SearchScreen(
        onCardClick: (GameCard) -> Unit
) {
    var searchInput by rememberSaveable { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()

    SearchContent(
            scaffoldState = scaffoldState,
            login = searchInput,
            onLoginChange = { searchInput = it },
            onClear = { searchInput = "" },
            gameCardList = emptyList(),
            onCardClick = onCardClick
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchContentPreview() {
    SearchContent(
            login = "",
            onLoginChange = {},
            onClear = {},
            gameCardList = listOf(GameCard(
                    1,
                    "Название игры",
                    "", 12,
                    "Экшен, Стратегия",
                    type = TypeDownload.DELETE)),
            onCardClick = {}
    )
}