package effective.office.marvelproject.presentation.chooseHero.viewModel

import effective.office.marvelproject.network.model.Character

sealed interface HeroesUiState {
    data class Success(val listHeroes: List<Character>) : HeroesUiState
    object Error : HeroesUiState
    object Loading : HeroesUiState
}
