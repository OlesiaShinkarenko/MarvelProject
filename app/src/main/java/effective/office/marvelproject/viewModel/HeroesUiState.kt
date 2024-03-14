package effective.office.marvelproject.viewModel

import effective.office.marvelproject.model.Hero
import effective.office.marvelproject.network.model.Character
sealed interface HeroesUiState {
    data class Success(val listHeroes: List<Character>) : HeroesUiState
    object Error : HeroesUiState
    object Loading : HeroesUiState
}
