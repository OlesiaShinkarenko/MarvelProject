package effective.office.marvelproject.presentation.hero.viewModel

import androidx.compose.runtime.Immutable
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.models.CharacterUI

@Immutable
data class HeroUiState(
    val isLoading: Boolean = true,
    val error: Int = R.string.unknown_error,
    val hero: CharacterUI = CharacterUI.Empty
) {
    companion object {
        val Empty = HeroUiState()
    }
}
