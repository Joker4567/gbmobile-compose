package pro.enaza.gb.shared_model.mapper

import pro.enaza.gb.shared_model.local.GameCard
import pro.enaza.gb.shared_model.network.GameCardDto
import pro.enaza.gb.shared_model.room.GameCardEntity

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

fun GameCard.toGameCardRoom() =
        GameCardEntity(
                id, name, imageUrl, age, tagsCategories, type
        )

fun GameCardEntity.toGameCard() =
        GameCard(id, name, imageUrl, age, tagsCategories, type)