package pro.enaza.gb.feature_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pro.enaza.gb.gbmobile_api.repository.CatalogRepository
import pro.enaza.gb.shared_model.local.GameCard
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
        private val repository: CatalogRepository
) : ViewModel() {
    private val _gameCardList = MutableStateFlow(emptyList<GameCard>())
    val gameCardList get() = _gameCardList.asStateFlow()

    fun searchGameCard(query: String = "") {
        viewModelScope.launch {
            if (query.isEmpty())
                _gameCardList.value = emptyList()
            else
                repository.getQueryGameCard(query)
                        .map { _gameCardList.value = it }
                        .flowOn(Dispatchers.IO)
                        .catch { e -> e.printStackTrace() }
                        .launchIn(viewModelScope)
        }
    }
}