package pro.enaza.gb.gbmobile_api.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import pro.enaza.gb.gbmobile_api.GBMobileApi
import pro.enaza.gb.gbmobile_api.result.isFailure
import pro.enaza.gb.gbmobile_api.result.isSuccess
import pro.enaza.gb.gbmobile_api.service.CatalogService
import pro.enaza.gb.gbmodile_room.db.AppDatabase
import pro.enaza.gb.gbmodile_room.db.GameCardDao
import pro.enaza.gb.shared_model.local.CatalogCategory
import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_model.local.GameCardCategory
import pro.enaza.gb.shared_model.mapper.toGameCard
import pro.enaza.gb.shared_model.mapper.toGameCardRoom
import pro.enaza.gb.shared_model.network.GameCardDto
import pro.enaza.gb.shared_model.room.GameCardEntity
import javax.inject.Inject

class CatalogRepository(
        private val catalogService: CatalogService,
        private val db: GameCardDao
) {

    @Inject
    constructor(devToApi: GBMobileApi, db: AppDatabase) : this(devToApi.catalog, db.gameCardDao())

    suspend fun getProduct(): List<CatalogCategory> {
        val response = catalogService.getProducts(1)
        return when {
            response.isSuccess() -> {
                db.clear()
                return convertToCategoryCard(response.value)
            }
            response.isFailure() -> {
                Log.e("CatalogRepository", response.error?.localizedMessage ?: "Error loading data")
                emptyList()
            }
            else -> error("Unhandled state")
        }
    }

    private suspend fun convertToCategoryCard(result: List<GameCardDto>): List<CatalogCategory> {
        val saveCollect = emptyList<CatalogCategory>().toMutableList()
        val gameCategory = emptyList<GameCardCategory>().toMutableList()
        result.forEach { gameCard ->
            val categories = gameCard.categories ?: emptyList()
            categories.forEach { nameCategory ->
                nameCategory.name?.let {
                    gameCategory.add(
                            GameCardCategory(nameCategory.name!!, gameCard.toGameCard())
                    )
                }
            }
            saveGameCard(gameCard.toGameCard().toGameCardRoom())
        }
        gameCategory.groupBy { x -> x.category }.forEach {
            saveCollect.add(CatalogCategory(it.key, it.value.map { it.data }))
        }
        return saveCollect.toList()
    }

    private suspend fun saveGameCard(gameCardEntity: GameCardEntity) {
        db.insert(gameCardEntity)
    }

    suspend fun getQueryGameCard(query: String): Flow<List<GameCard>> = flow { emit(db.getQuery(query)) }
            .map { it.map { r -> r.toGameCard() } }
}