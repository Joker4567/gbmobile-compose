package pro.enaza.gb.mobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentManager
import androidx.navigation.*
import androidx.navigation.compose.composable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import pro.enaza.gb.feature_card.CardDialog
import pro.enaza.gb.feature_catalog.CatalogScreen
import pro.enaza.gb.feature_search.SearchScreen
import pro.enaza.gb.feature_subcatalog.SubCatalogScreen
import pro.enaza.gb.gbmobile_theme.main.HomeSections
import pro.enaza.gb.gbmobile_theme.main.MainDestinations
import pro.enaza.gb.shared_model.local.CatalogCategory
import pro.enaza.gb.shared_model.local.GameCard

internal fun NavGraphBuilder.navGraph(
        onCardSelected: (GameCard, NavBackStackEntry) -> Unit,
        onSubCatalogSelected: (CatalogCategory, NavBackStackEntry) -> Unit,
        upPress: () -> Unit
) {
    navigation(
            route = MainDestinations.HOME_ROUTE,
            startDestination = HomeSections.CATALOG.route
    ) {
        addHomeGraph(onCardSelected, onSubCatalogSelected, upPress)
    }
}

private fun NavGraphBuilder.addHomeGraph(
        onCardSelected: (GameCard, NavBackStackEntry) -> Unit,
        onSubCatalogSelected: (CatalogCategory, NavBackStackEntry) -> Unit,
        upPress: () -> Unit,
        modifier: Modifier = Modifier
) {
    composable(HomeSections.CATALOG.route) { from ->
        CatalogScreen(
                onCardClick = { id ->
                    onCardSelected(id, from)
                },
                modifier = modifier,
                onSubCatalog = { id ->
                    onSubCatalogSelected(id, from)
                }
        )
    }
    composable(HomeSections.PROFILE.route) {

    }
    composable(HomeSections.SEARCH.route) { from ->
        SearchScreen(
                onCardClick = { id ->
                    onCardSelected(id, from)
                }
        )
    }
    composable(
            route = "${MainDestinations.GAME_CARD_DETAIL_ROUTE}/{${MainDestinations.GAME_CARD}}",
            arguments = listOf(navArgument(MainDestinations.GAME_CARD) { type = NavType.StringType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        arguments.getString(MainDestinations.GAME_CARD)?.let { cardDataString ->
            val card = Json.decodeFromString<GameCard>(cardDataString)
            CardDialog(card, upPress, {}, {})
        }
    }
    composable(
            route = "${MainDestinations.SUB_CATALOG_ROUTE}/{${MainDestinations.CATALOG_GAME}}",
            arguments = listOf(navArgument(MainDestinations.CATALOG_GAME) { type = NavType.StringType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        arguments.getString(MainDestinations.CATALOG_GAME)?.let { cardDataString ->
            val card = Json.decodeFromString<CatalogCategory>(cardDataString)
            SubCatalogScreen(onCardClick = { id ->
                onCardSelected(id, backStackEntry)
            }, upPress = upPress, categoryName = card.category, gameCardList = card.data)
        }
    }
}