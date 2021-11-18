package pro.enaza.gb.shared_model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameCardDto(
        @SerialName("id")
        var id: Long = 0,
        @SerialName("name")
        var name: String = "",
        @SerialName("description")
        var description: String = "",
        @SerialName("categories")
        var categories: List<CategoryDto>? = emptyList(),
        @SerialName("coverBigUrl")
        var coverBigUrl: String = "",
        @SerialName("coverUrl")
        var coverUrl: String = "",
        @SerialName("imageUrl")
        var imageUrl: List<String>?,
        @SerialName("sale")
        var sale: Int = 0,
        @SerialName("download_url")
        var download_url:String = "",
        @SerialName("game_uid")
        var game_uid: String = "",
        @SerialName("age")
        var age:Int = 0
)