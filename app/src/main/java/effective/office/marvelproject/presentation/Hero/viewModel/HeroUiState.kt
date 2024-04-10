package effective.office.marvelproject.presentation.Hero.viewModel

import effective.office.marvelproject.model.CharacterUI

interface HeroUiState {
    data class Success(val hero: CharacterUI) : HeroUiState
    object Loading : HeroUiState
    data class Error(val error: Int) : HeroUiState
}