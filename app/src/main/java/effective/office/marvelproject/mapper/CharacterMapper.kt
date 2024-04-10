package effective.office.marvelproject.mapper

import effective.office.marvelproject.data.network.model.Character
import effective.office.marvelproject.model.CharacterUI

fun Character.toUI() = CharacterUI(
    id = id,
    name = name,
    description = description,
    logo = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}"
)