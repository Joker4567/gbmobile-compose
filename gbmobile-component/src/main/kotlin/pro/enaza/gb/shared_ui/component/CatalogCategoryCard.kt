package pro.enaza.gb.shared_ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.statusBarsPadding
import pro.enaza.gb.shared_model.local.CatalogCategory
import pro.enaza.gb.shared_model.local.GameCard

@Composable
fun CatalogCategoryCard(
        onCardClick: (GameCard) -> Unit,
        onSubCatalog: (CatalogCategory) -> Unit,
        catalogList: List<CatalogCategory>,
        modifier: Modifier = Modifier
) {
    GbSurface(modifier = modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.statusBarsPadding())
            CartCategoryContent(
                    cardList = catalogList,
                    onSubCatalog = onSubCatalog,
                    onCardClick = onCardClick
            )
        }
    }
}

@Composable
fun CartCategoryContent(
        cardList: List<CatalogCategory>,
        onSubCatalog: (CatalogCategory) -> Unit,
        onCardClick: (GameCard) -> Unit,
        modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(cardList) { catalogContent ->
            CategoryHeader(catalogContent.category, onSubCatalog, catalogContent.data)
            catalogContent.data.take(5).forEach { gameCard ->
                CartItem(
                        card = gameCard,
                        onCardClick = onCardClick
                )
            }
        }
    }
}