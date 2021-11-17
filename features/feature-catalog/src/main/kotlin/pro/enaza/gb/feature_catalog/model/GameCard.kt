package pro.enaza.gb.feature_catalog.model

data class GameCard(
        val id: Long,
        val name: String = "",
        val imageUrl: String = "",
        val age:Int = 0,
        val tagsCategories: String,
        val type: TypeDownload = TypeDownload.WAIT
)

enum class TypeDownload {
    DOWNLOAD, WAIT, INSTALL, PLAY, ERROR, DELETE
}
