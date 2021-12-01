package pro.enaza.gb.feature_search.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import pro.enaza.gb.gbmobile_theme.theme.uiBackground
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_ui.component.CatalogCard
import pro.enaza.gb.shared_ui.component.SearchBar

@Composable
internal fun SearchContent(
        scaffoldState: ScaffoldState = rememberScaffoldState(),
        query: String,
        onQueryChange: (String) -> Unit,
        gameCardList: List<GameCard>,
        onCardClick: (GameCard) -> Unit
) {
    Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = uiBackground
    ) {
        Box(Modifier.fillMaxSize()) {
            Column(
                    Modifier.align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.statusBarsPadding())
                Text(
                        text = "Поиск по каталогу".uppercase(),
                        style = MaterialTheme.typography.h6,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 30.dp, bottom = 15.dp)
                )
                SearchBar(
                        query = query,
                        onQueryChange = onQueryChange
                )
                CatalogCard(
                        onCardClick = onCardClick,
                        cardList = gameCardList,
                        modifier = Modifier.padding(top = 25.dp, bottom = 10.dp)
                )
            }
        }
    }
}