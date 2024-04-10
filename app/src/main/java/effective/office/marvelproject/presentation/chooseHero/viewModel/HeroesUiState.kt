package effective.office.marvelproject.presentation.chooseHero.viewModel

import effective.office.marvelproject.model.CharacterUI

sealed interface HeroesUiState {
    data class Success(val listHeroes: List<CharacterUI>) : HeroesUiState
    data class Error(val message: Int) : HeroesUiState
    object Loading : HeroesUiState
}
