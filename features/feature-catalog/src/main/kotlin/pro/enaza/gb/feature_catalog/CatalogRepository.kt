package pro.enaza.gb.feature_catalog

import android.util.Log
import pro.enaza.gb.gbmobile_api.GBMobileApi
import pro.enaza.gb.gbmobile_api.result.isFailure
import pro.enaza.gb.gbmobile_api.result.isSuccess
import pro.enaza.gb.gbmobile_api.service.CatalogService
import pro.enaza.gb.shared_model.network.GameCardDto
import javax.inject.Inject

class CatalogRepository(
        private val catalogService: CatalogService
) {

    @Inject
    constructor(devToApi: GBMobileApi) : this(devToApi.catalog)

    suspend fun getProduct(): List<GameCardDto> {
        val response = catalogService.getProducts(1)
        return when {
            response.isSuccess() -> {
                return response.value
            }
            response.isFailure() -> {
                Log.e("CatalogRepository", response.error?.localizedMessage ?: "Error loading data")
                emptyList()
            }
            else -> error("Unhandled state")
        }
    }
}