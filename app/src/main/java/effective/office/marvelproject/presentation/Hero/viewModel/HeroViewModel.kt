package effective.office.marvelproject.presentation.Hero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import effective.office.marvelproject.data.network.either.Either
import effective.office.marvelproject.domain.repositories.MarvelRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    ViewModel() {
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
}