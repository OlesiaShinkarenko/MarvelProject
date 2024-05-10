package effective.office.marvelproject.data.remote.model.mappers

import effective.office.marvelproject.data.remote.model.Character
import effective.office.marvelproject.domain.model.CharacterEntity

fun Character.toEntity() = CharacterEntity(
    id = id,
    name = name,
    description = description,
    image = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}"
)