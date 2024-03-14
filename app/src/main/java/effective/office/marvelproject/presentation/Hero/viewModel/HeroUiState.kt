package effective.office.marvelproject.presentation.Hero.viewModel

import effective.office.marvelproject.network.model.Character
interface HeroUiState {
    data class Success(val hero:Character): HeroUiState
    object Loading: HeroUiState
    object Error: HeroUiState
}