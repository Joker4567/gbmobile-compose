package pro.enaza.gb.shared_model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
        @SerialName("id")
        var id: Long?,
        @SerialName("name")
        var name: String?
)