package pro.enaza.gb.shared_model.local

import kotlinx.serialization.Serializable

@Serializable
data class CatalogCategory(
     val category: String,
     val data: List<GameCard>
)

data class GameCardCategory(
        val category: String,
        val data: GameCard
)
