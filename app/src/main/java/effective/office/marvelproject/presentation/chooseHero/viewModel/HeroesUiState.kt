package effective.office.marvelproject.presentation.chooseHero.viewModel

import effective.office.marvelproject.model.HeroUI

sealed interface HeroesUiState {
    data class Success(val listHeroes: List<HeroUI>) : HeroesUiState
    data class Error(val message: Int) : HeroesUiState
    object Loading : HeroesUiState
}
