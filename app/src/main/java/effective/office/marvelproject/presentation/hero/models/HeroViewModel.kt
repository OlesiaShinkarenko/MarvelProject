package effective.office.marvelproject.presentation.hero.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import effective.office.marvelproject.data.network.Either
import effective.office.marvelproject.data.repository.MarvelRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(private val repository: MarvelRepository) :
    ViewModel() {
    private val _heroEvent = Channel<HeroEvent>(Channel.UNLIMITED)
    private val _uiState = MutableStateFlow(HeroUiState.Empty)
    val state: StateFlow<HeroUiState> = _uiState.asStateFlow()

    init {
        handleEvent()
    }

    private fun handleEvent() {
        viewModelScope.launch {
            _heroEvent.consumeAsFlow().collect {
                when (it) {
                    is HeroEvent.FetchHero -> fetchHero(it.id)
                }
            }
        }
    }

    fun sendEvent(heroEvent: HeroEvent) {
        viewModelScope.launch {
            _heroEvent.send(heroEvent)
        }
    }

    private fun fetchHero(id: Int) {
        viewModelScope.launch {
            repository.getCharacter(id).collect {
                when (it) {
                    is Either.Fail -> {
                        _uiState.update { state ->
                            state.copy(
                                error = it.value.description
                            )
                        }
                    }

                    is Either.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                hero = it.value
                            )
                        }
                    }
                }
            }
        }
    }
}