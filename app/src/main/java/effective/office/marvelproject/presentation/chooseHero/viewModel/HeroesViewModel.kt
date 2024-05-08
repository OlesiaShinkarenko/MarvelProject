package effective.office.marvelproject.presentation.chooseHero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import effective.office.marvelproject.domain.repositories.MarvelRepositoryImpl
import effective.office.marvelproject.presentation.model.CharacterUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) : ViewModel() {
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
}