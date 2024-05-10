package effective.office.marvelproject.presentation.models.mappers

import effective.office.marvelproject.domain.model.CharacterEntity
import effective.office.marvelproject.presentation.models.CharacterUI



fun CharacterEntity.toUI() = CharacterUI(
    id = id,
    name = name,
    description = description,
    logo = image
)