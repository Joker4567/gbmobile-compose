package pro.enaza.gb.feature_catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import pro.enaza.gb.feature_catalog.model.GameCard
import pro.enaza.gb.feature_catalog.model.toGameCard
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject internal constructor(
        private val repository: Lazy<CatalogRepository>
) : ViewModel() {

    //TODO bad code, подумать над вариантом единоразового получения flow без условий
    var saveCollect = emptyList<GameCard>()
    var isLoad = false

    fun getProducts(): Flow<List<GameCard>> = flow {
        val result = repository.get().getProduct().map { it.toGameCard() }
        if(result.isNotEmpty() && isLoad.not()) {
            isLoad = true
            saveCollect = result
            emit(result)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), saveCollect)
}