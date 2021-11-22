package pro.enaza.gb.feature_catalog

import pro.enaza.gb.core.base.ViewEvent
import pro.enaza.gb.core.base.ViewSideEffect
import pro.enaza.gb.core.base.ViewState
import pro.enaza.gb.shared_model.local.CatalogCategory

class CatalogContract {
    sealed class Event : ViewEvent {

    }

    data class State(val gameCards: List<CatalogCategory> = listOf(), val isLoading: Boolean = false) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {

        }
    }

}