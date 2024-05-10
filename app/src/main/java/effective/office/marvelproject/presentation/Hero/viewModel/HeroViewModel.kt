package effective.office.marvelproject.presentation.Hero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import effective.office.marvelproject.MarvelApplication
import effective.office.marvelproject.data.network.either.Either
import effective.office.marvelproject.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeroViewModel(private val repository: MarvelRepository) : ViewModel() {
    private var _uiState = MutableStateFlow(HeroUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.emit(
                HeroUiState(isLoading = true)
            )
        }
    }

    fun fetchHero(id: Int) {
        viewModelScope.launch {
            when (val response = repository.getCharacter(id)) {
                is Either.Fail -> {
                    _uiState.emit(
                        HeroUiState(
                            isLoading = false,
                            errorMessage = response.value.description
                        )
                    )
                }

                is Either.Success -> {
                    _uiState.emit(
                        HeroUiState(
                            isLoading = false,
                            hero = response.value
                        )
                    )
                }
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarvelApplication
                HeroViewModel(app.repository)
            }
        }
    }
}