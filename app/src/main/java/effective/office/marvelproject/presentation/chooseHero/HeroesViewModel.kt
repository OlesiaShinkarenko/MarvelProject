package effective.office.marvelproject.presentation.chooseHero

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import effective.office.marvelproject.R
import effective.office.marvelproject.data.repository.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(private val repository: MarvelRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(HeroesUiState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        fetchHeroes()
    }

    private fun fetchHeroes() {
        viewModelScope.launch {
            try {
                val heroes = repository.getCharacterPage()
                    .cachedIn(viewModelScope)
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        heroes = heroes
                    )
                }
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = e.message?.toInt() ?: R.string.unknown_error
                    )
                }
            }
        }
    }
}