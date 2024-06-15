package effective.office.marvelproject.presentation.chooseHero.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import effective.office.marvelproject.R
import effective.office.marvelproject.data.repository.MarvelRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val repository: MarvelRepository,
) :
    ViewModel() {
    private val _heroesEvent = Channel<HeroesEvent>(Channel.UNLIMITED)
    private val _uiState = MutableStateFlow(HeroesUiState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        handleEvent()
        sendEvent(HeroesEvent.FetchHeroes)
    }

    private fun handleEvent() {
        viewModelScope.launch {
            _heroesEvent.consumeAsFlow().collect {
                when (it) {
                    is HeroesEvent.FetchHeroes -> fetchHeroes()
                }
            }
        }
    }

    fun sendEvent(heroesEvent: HeroesEvent) {
        viewModelScope.launch {
            _heroesEvent.send(heroesEvent)
        }
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