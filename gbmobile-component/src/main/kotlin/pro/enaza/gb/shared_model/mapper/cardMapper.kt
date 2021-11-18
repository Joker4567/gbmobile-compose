package pro.enaza.gb.shared_model.mapper

import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_model.network.GameCardDto

fun GameCardDto.toGameCard() =
        GameCard(
                id = id,
                name = name,
                imageUrl = coverBigUrl,
                age = age,
                tagsCategories =
                ((categories?.map { "${it.name}" } ?: "").toString())
                        .replace("[","")
                        .replace("]", "")
                        .trimEnd(',')
        )