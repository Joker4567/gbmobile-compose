package pro.enaza.gb.mobile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import pro.enaza.gb.feature_catalog.CatalogScreen
import pro.enaza.gb.gbmobile_theme.DevToTheme
import pro.enaza.gb.gbmobile_theme.main.BottomBar
import pro.enaza.gb.gbmobile_theme.main.HomeSections
import pro.enaza.gb.gbmobile_theme.main.MainDestinations
import pro.enaza.gb.gbmobile_theme.main.rememberAppState
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
                            onSnackSelected = appState::navigateToSnackDetail,
                            upPress = appState::upPress
                    )
                }
            }
        }
    }
}

fun NavGraphBuilder.navGraph(
        onSnackSelected: (Long, NavBackStackEntry) -> Unit,
        upPress: () -> Unit
) {
    navigation(
            route = MainDestinations.HOME_ROUTE,
            startDestination = HomeSections.CATALOG.route
    ) {
        addHomeGraph(onSnackSelected)
    }
}

fun NavGraphBuilder.addHomeGraph(
        onSnackSelected: (Long, NavBackStackEntry) -> Unit,
        modifier: Modifier = Modifier
) {
    composable(HomeSections.CATALOG.route) { from ->
        CatalogScreen()
    }
    composable(HomeSections.PROFILE.route) {

    }
    composable(HomeSections.SEARCH.route) { from ->

    }
}
