package pro.enaza.gb.mobile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import pro.enaza.gb.feature_card.CardDialog
import pro.enaza.gb.feature_catalog.CatalogScreen
import pro.enaza.gb.gbmobile_theme.DevToTheme
import pro.enaza.gb.gbmobile_theme.main.BottomBar
import pro.enaza.gb.gbmobile_theme.main.HomeSections
import pro.enaza.gb.gbmobile_theme.main.MainDestinations
import pro.enaza.gb.gbmobile_theme.main.rememberAppState
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_ui.component.GbScaffold
import pro.enaza.gb.shared_ui.component.Snackbar

@Composable
fun GbApp() {
    ProvideWindowInsets {
        DevToTheme {
            val appState = rememberAppState()
            GbScaffold(
                    bottomBar = {
                        if (appState.shouldShowBottomBar) {
                            BottomBar(
                                    tabs = appState.bottomBarTabs,
                                    currentRoute = appState.currentRoute!!,
                                    navigateToRoute = appState::navigateToBottomBarRoute
                            )
                        }
                    },
                    snackbarHost = {
                        SnackbarHost(
                                hostState = it,
                                modifier = Modifier.systemBarsPadding(),
                                snackbar = { snackbarData -> Snackbar(snackbarData) }
                        )
                    },
                    scaffoldState = appState.scaffoldState
            ) { innerPaddingModifier ->
                NavHost(
                        navController = appState.navController,
                        startDestination = MainDestinations.HOME_ROUTE,
                        modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    navGraph(
                            onCardSelected = appState::navigateToSnackDetail,
                            upPress = appState::upPress
                    )
                }
            }
        }
    }
}

fun NavGraphBuilder.navGraph(
        onCardSelected: (GameCard, NavBackStackEntry) -> Unit,
        upPress: () -> Unit
) {
    navigation(
            route = MainDestinations.HOME_ROUTE,
            startDestination = HomeSections.CATALOG.route
    ) {
        addHomeGraph(onCardSelected)
    }
    composable(
            "${MainDestinations.SNACK_DETAIL_ROUTE}/{${MainDestinations.GAME_CARD}}",
            arguments = listOf(navArgument(MainDestinations.GAME_CARD) { type = NavType.StringType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        arguments.getString(MainDestinations.GAME_CARD)?.let { cardDataString ->
            val card = Json.decodeFromString<GameCard>(cardDataString)
            CardDialog(card, upPress, {}, {})
        }

    }
}

fun NavGraphBuilder.addHomeGraph(
        onCardSelected: (GameCard, NavBackStackEntry) -> Unit,
        modifier: Modifier = Modifier
) {
    composable(HomeSections.CATALOG.route) { from ->
        CatalogScreen(
                onCardClick = { id ->
                    onCardSelected(id, from)
                },
                modifier = modifier)
    }
    composable(HomeSections.PROFILE.route) {

    }
    composable(HomeSections.SEARCH.route) { from ->

    }
}
