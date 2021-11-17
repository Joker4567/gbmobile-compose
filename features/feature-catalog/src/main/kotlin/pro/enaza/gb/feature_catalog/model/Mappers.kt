package pro.enaza.gb.feature_catalog.model

import pro.enaza.gb.gbmobile_api.api.entity.GameCardDto

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