package pro.enaza.gb.gbmobile_theme.main

import android.content.res.Resources
import android.os.Bundle
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pro.enaza.gb.gbmobile_theme.main.MainDestinations.GAME_CARD
import pro.enaza.gb.gbmobile_theme.main.MainDestinations.HOME_ROUTE
import pro.enaza.gb.shared_model.local.GameCard

/**
 * Destinations used in the [GbApp].
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val SNACK_DETAIL_ROUTE = "snack"
    const val GAME_CARD = "gameCard"
}

/**
 * Remembers and creates an instance of [AppState]
 */
@Composable
fun rememberAppState(
        scaffoldState: ScaffoldState = rememberScaffoldState(),
        navController: NavHostController = rememberNavController(),
        resources: Resources = resources(),
        coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
        remember(scaffoldState, navController, resources, coroutineScope) {
            GbAppState(scaffoldState, navController, resources, coroutineScope)
        }

/**
 * Responsible for holding state related to [GbApp] and containing UI-related logic.
 */
@Stable
class GbAppState(
        val scaffoldState: ScaffoldState,
        val navController: NavHostController,
        private val resources: Resources,
        coroutineScope: CoroutineScope
) {
    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    val bottomBarTabs = HomeSections.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
                .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                // Pop up backstack to the first destination and save state. This makes going back
                // to the start destination when pressing back in any other bottom tab.
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToSnackDetail(game: GameCard, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            val json = Json.encodeToString(game)
            navController.navigate("${MainDestinations.SNACK_DETAIL_ROUTE}/$json")
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
        this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
