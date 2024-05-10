package effective.office.marvelproject.presentation.Hero.viewModel

sealed class HeroIntent {
    data class FetchHero(val id: Int) : HeroIntent()
}