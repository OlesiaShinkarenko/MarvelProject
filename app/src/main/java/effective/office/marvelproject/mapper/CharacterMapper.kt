package effective.office.marvelproject.mapper

import effective.office.marvelproject.data.db.models.CharacterEntity
import effective.office.marvelproject.data.network.model.Character

fun Character.toEntity() = CharacterEntity(
    id = id,
    name = name,
    description = description,
    image = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}"
)