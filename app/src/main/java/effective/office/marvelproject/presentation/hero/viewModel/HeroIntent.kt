package effective.office.marvelproject.presentation.hero.viewModel

sealed class HeroIntent {
    data class FetchHero(val id: Int) : HeroIntent()
}