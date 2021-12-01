package pro.enaza.gb.feature_search

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import pro.enaza.gb.feature_search.component.SearchContent
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_model.local.TypeDownload

@Composable
fun SearchScreen(
        onCardClick: (GameCard) -> Unit,
        viewModel: SearchViewModel = hiltViewModel()
) {
    var searchInput by rememberSaveable { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()

    val list = viewModel.gameCardList.collectAsState()

    SearchContent(
            scaffoldState = scaffoldState,
            query = searchInput,
            onQueryChange = {
                searchInput = it
                viewModel.searchGameCard(it)
            },
            gameCardList = list.value,
            onCardClick = onCardClick
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchContentPreview() {
    SearchContent(
            query = "",
            onQueryChange = {},
            gameCardList = listOf(GameCard(
                    1,
                    "Название игры",
                    "", 12,
                    "Экшен, Стратегия",
                    type = TypeDownload.DELETE)),
            onCardClick = {}
    )
}