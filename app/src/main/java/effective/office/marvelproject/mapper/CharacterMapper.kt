package effective.office.marvelproject.mapper

import effective.office.marvelproject.data.network.model.Character
import effective.office.marvelproject.model.HeroUI

fun Character.toUI() = HeroUI(
    id = id,
    name = name,
    description = description,
    logo = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}"
)