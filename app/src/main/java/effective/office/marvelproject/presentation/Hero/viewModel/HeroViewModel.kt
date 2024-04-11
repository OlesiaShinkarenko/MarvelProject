package effective.office.marvelproject.presentation.Hero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import effective.office.marvelproject.MarvelApplication
import effective.office.marvelproject.repositories.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeroViewModel(private val repository: MarvelRepository) : ViewModel() {
    private var _uiState = MutableStateFlow<HeroUiState>(HeroUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchHero(id: Int) {
        viewModelScope.launch {
            _uiState.value = HeroUiState.Loading
            val response = repository.getCharacter(id)
            _uiState.value = HeroUiState.Success(response)
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