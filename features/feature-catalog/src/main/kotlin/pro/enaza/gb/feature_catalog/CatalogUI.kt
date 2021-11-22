package pro.enaza.gb.feature_catalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.onEach
import pro.enaza.gb.shared_model.local.CatalogCategory
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_ui.component.CatalogCategoryCard
import pro.enaza.gb.shared_ui.component.ScreenProgress

@Composable
fun CatalogScreen(
        onCardClick: (GameCard) -> Unit,
        onSubCatalog: (CatalogCategory) -> Unit,
        modifier: Modifier = Modifier,
        viewModel: CatalogViewModel = hiltViewModel()
) {
    val state = viewModel.viewState.value

    if (state.isLoading)
        ScreenProgress()
    else {
        CatalogCategoryCard(
                onCardClick = onCardClick,
                catalogList = state.gameCards,
                modifier = modifier,
                onSubCatalog = onSubCatalog
        )
    }
}