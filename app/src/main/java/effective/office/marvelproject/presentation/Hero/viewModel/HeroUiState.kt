package effective.office.marvelproject.presentation.Hero.viewModel

import effective.office.marvelproject.model.CharacterUI

data class HeroUiState(
    val hero: CharacterUI? = null,
    val isLoading: Boolean = false,
    val errorMessage: Int? = null
)