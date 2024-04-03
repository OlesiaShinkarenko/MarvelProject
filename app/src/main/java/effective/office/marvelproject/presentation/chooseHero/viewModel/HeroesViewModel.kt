package effective.office.marvelproject.presentation.chooseHero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import effective.office.marvelproject.model.HeroUI
import effective.office.marvelproject.network.paging.MarvelPagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeroesViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<PagingData<HeroUI>> =
        MutableStateFlow(PagingData.empty())
    val uiState = _uiState.asStateFlow()

    private fun marvelPagingSource() = MarvelPagingSource()

    init {
        fetchHeroes()
    }

    private fun fetchHeroes() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10,
                    enablePlaceholders = true
                )
            ) {
                marvelPagingSource()
            }.flow.cachedIn(viewModelScope).collect {
                _uiState.value = it
            }
        }
    }
}