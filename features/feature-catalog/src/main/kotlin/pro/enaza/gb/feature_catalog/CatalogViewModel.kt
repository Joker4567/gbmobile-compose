package pro.enaza.gb.feature_catalog

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pro.enaza.gb.core.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(private val repository: CatalogRepository) :
        BaseViewModel<CatalogContract.Event, CatalogContract.State, CatalogContract.Effect>() {

    init {
        viewModelScope.launch { getGameCards() }
    }

    override fun setInitialState() =
            CatalogContract.State(gameCards = listOf(), isLoading = true)

    private suspend fun getGameCards() {
        val categories = repository.getProduct()
        setState {
            copy(gameCards = categories, isLoading = false)
        }
        setEffect { CatalogContract.Effect.DataWasLoaded }
    }

}