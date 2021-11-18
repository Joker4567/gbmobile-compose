package pro.enaza.gb.feature_catalog

import android.util.Log
import pro.enaza.gb.gbmobile_api.GBMobileApi
import pro.enaza.gb.gbmobile_api.result.isFailure
import pro.enaza.gb.gbmobile_api.result.isSuccess
import pro.enaza.gb.gbmobile_api.service.CatalogService
import pro.enaza.gb.shared_model.local.CatalogCategory
import pro.enaza.gb.shared_model.local.GameCardCategory
import pro.enaza.gb.shared_model.mapper.toGameCard
import pro.enaza.gb.shared_model.network.GameCardDto
import javax.inject.Inject

class CatalogRepository(
        private val catalogService: CatalogService
) {

    @Inject
    constructor(devToApi: GBMobileApi) : this(devToApi.catalog)

    suspend fun getProduct(): List<CatalogCategory> {
        val response = catalogService.getProducts(1)
        return when {
            response.isSuccess() -> {
                return convertToCategoryCard(response.value)
            }
            response.isFailure() -> {
                Log.e("CatalogRepository", response.error?.localizedMessage ?: "Error loading data")
                emptyList()
            }
            else -> error("Unhandled state")
        }
    }

    private fun convertToCategoryCard(result: List<GameCardDto>) : List<CatalogCategory> {
        var saveCollect = emptyList<CatalogCategory>().toMutableList()
        var gameCategory = emptyList<GameCardCategory>().toMutableList()
        result.forEach { gameCard ->
            val categories = gameCard.categories ?: emptyList()
            categories.forEach { nameCategory ->
                nameCategory.name?.let {
                    gameCategory.add(
                            GameCardCategory(nameCategory.name!!, gameCard.toGameCard())
                    )
                }
            }
        }
        gameCategory.groupBy { x -> x.category }.forEach {
            saveCollect.add(CatalogCategory(it.key, it.value.map { it.data }))
        }
        return saveCollect.toList()
    }
}