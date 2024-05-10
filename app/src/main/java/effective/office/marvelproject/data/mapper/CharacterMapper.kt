package effective.office.marvelproject.data.mapper

import effective.office.marvelproject.data.db.models.CharacterEntity
import effective.office.marvelproject.data.network.model.Character
import effective.office.marvelproject.presentation.model.CharacterUI

fun Character.toEntity() = CharacterEntity(
    id = id,
    name = name,
    description = description,
    image = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}"
)

fun CharacterEntity.toUI() = CharacterUI(
    id = id,
    name = name,
    description = description,
    logo = image
)