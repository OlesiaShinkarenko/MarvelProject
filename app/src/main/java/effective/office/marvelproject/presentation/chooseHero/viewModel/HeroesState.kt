package effective.office.marvelproject.presentation.chooseHero.viewModel

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import effective.office.marvelproject.presentation.model.CharacterUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class HeroesState(
    val isLoading: Boolean = true,
    val error: Int? = null,
    val heroes: Flow<PagingData<CharacterUI>> = emptyFlow()
) {
    companion object {
        val Empty = HeroesState()
    }
}