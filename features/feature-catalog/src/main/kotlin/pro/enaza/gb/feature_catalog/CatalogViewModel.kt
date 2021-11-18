package pro.enaza.gb.feature_catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import pro.enaza.gb.shared_model.local.CatalogCategory
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_model.mapper.toGameCard
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject internal constructor(
        private val repository: Lazy<CatalogRepository>
) : ViewModel() {

    //TODO bad code, подумать над вариантом единоразового получения flow без условий
    var saveCollect = emptyList<CatalogCategory>()
    var isLoad = false

    fun getProducts(): Flow<List<CatalogCategory>> = flow {
        val result = repository.get().getProduct()
        if(result.isNotEmpty() && isLoad.not()) {
            isLoad = true
            saveCollect = result
            emit(result)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), saveCollect)
}