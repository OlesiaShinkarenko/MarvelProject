package effective.office.marvelproject.presentation.hero

sealed class HeroIntent {
    data class FetchHero(val id: Int) : HeroIntent()
}