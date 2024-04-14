package effective.office.marvelproject.mapper

import effective.office.marvelproject.model.HeroUI
import effective.office.marvelproject.network.model.Character

fun Character.toUI() = HeroUI(
    id = id,
    name = name,
    description = description,
    logo = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}"
)