package effective.office.marvelproject.presentation.Hero.viewModel

import androidx.compose.runtime.Immutable
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.model.CharacterUI

@Immutable
data class HeroState(
    val isLoading: Boolean = true,
    val error: Int = R.string.unknown_error,
    val hero: CharacterUI = CharacterUI.Empty
) {
    companion object {
        val Empty = HeroState()
    }
}
