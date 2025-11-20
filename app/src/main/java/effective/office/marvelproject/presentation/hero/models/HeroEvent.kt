package effective.office.marvelproject.presentation.hero.models

sealed class HeroEvent {
    data class FetchHero(val id: Int) : HeroEvent()
}