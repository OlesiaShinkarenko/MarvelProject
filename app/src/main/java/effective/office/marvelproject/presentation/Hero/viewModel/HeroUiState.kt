package effective.office.marvelproject.presentation.Hero.viewModel

import effective.office.marvelproject.model.HeroUI

interface HeroUiState {
    data class Success(val hero: HeroUI) : HeroUiState
    object Loading : HeroUiState
    object Error : HeroUiState
}