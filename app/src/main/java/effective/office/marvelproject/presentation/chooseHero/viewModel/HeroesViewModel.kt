package effective.office.marvelproject.presentation.chooseHero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import effective.office.marvelproject.MarvelApplication
import effective.office.marvelproject.domain.repositories.MarvelRepository
import effective.office.marvelproject.presentation.model.CharacterUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeroesViewModel(private val repository: MarvelRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<PagingData<CharacterUI>> = MutableStateFlow(PagingData.empty())
    val uiState = _uiState.asStateFlow()

    init {
        fetchHeroes()
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun fetchHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacterPage()
                .cachedIn(viewModelScope).collect {
                    _uiState.value = it
                }
        }
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarvelApplication
                HeroesViewModel(app.repository)
            }
        }
    }
}