package effective.office.marvelproject.presentation.chooseHero.models

sealed class HeroesEvent {
    data object FetchHeroes : HeroesEvent()
}