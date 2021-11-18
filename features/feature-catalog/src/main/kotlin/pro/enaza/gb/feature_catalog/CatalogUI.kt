package pro.enaza.gb.feature_catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
    val collect = viewModel.getProducts().collectAsState(emptyList())
    if(collect.value.isNotEmpty() && viewModel.isLoad)
        CatalogCategoryCard(
                onCardClick = onCardClick,
                catalogList = collect.value,
                modifier = modifier,
                onSubCatalog = onSubCatalog
        )
    else if(viewModel.isLoad.not())
        ScreenProgress()
}