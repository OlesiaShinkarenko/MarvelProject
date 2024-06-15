package effective.office.marvelproject.presentation.hero.models

import androidx.compose.runtime.Immutable
import effective.office.marvelproject.presentation.models.CharacterUI

@Immutable
data class HeroUiState(
    val isLoading: Boolean = true,
    val error: Int? = null,
    val hero: CharacterUI? = null
) {
    companion object {
        val Empty = HeroUiState()
    }
}
