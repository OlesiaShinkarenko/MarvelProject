package effective.office.marvelproject.viewModel

import effective.office.marvelproject.presentation.model.Hero

data class HeroUiState(
    val listHeroes: List<Hero> = listOf()
)
