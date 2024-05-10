package effective.office.marvelproject.presentation.Hero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import effective.office.marvelproject.data.network.Either
import effective.office.marvelproject.domain.repositories.MarvelRepositoryImpl
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    ViewModel() {
    private val heroIntent = Channel<HeroIntent>(Channel.UNLIMITED)
    private val _uiState = MutableStateFlow(HeroState.Empty)
    val state: StateFlow<HeroState> = _uiState.asStateFlow()

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            heroIntent.consumeAsFlow().collect {
                when (it) {
                    is HeroIntent.FetchHero -> fetchHero(it.id)
                }
            }
        }
    }

    fun fetchHero(id: Int) {
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