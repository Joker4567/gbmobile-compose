package pro.enaza.gb.feature_catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_ui.component.*

@Composable
fun CatalogScreen(
        onCardClick: (GameCard) -> Unit,
        modifier: Modifier = Modifier,
        viewModel: CatalogViewModel = hiltViewModel()
) {
    val collect = viewModel.getProducts().collectAsState(emptyList())
    if(collect.value.isNotEmpty() && viewModel.isLoad)
        Cart(
                onCardClick = onCardClick,
                cardList = collect.value,
                modifier = modifier
        )
    else if(viewModel.isLoad.not())
        ScreenProgress()
}
