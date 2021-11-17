package pro.enaza.gb.feature_catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pro.enaza.gb.feature_catalog.component.Cart
import pro.enaza.gb.feature_catalog.component.ScreenProgress

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CatalogScreen(
        modifier: Modifier = Modifier,
        viewModel: CatalogViewModel = hiltViewModel()
) {
    val collect = viewModel.getProducts().collectAsState(emptyList())
    if(collect.value.isNotEmpty() && viewModel.isLoad)
        Cart(collect.value, modifier)
    else if(viewModel.isLoad.not())
        ScreenProgress()
}
