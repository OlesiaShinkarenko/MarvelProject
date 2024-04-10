package effective.office.marvelproject.presentation.chooseHero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import effective.office.marvelproject.model.HeroUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeroesViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<PagingData<HeroUI>> =
        MutableStateFlow(PagingData.empty())
    val uiState = _uiState.asStateFlow()



    init {
        fetchHeroes()
    }

    private fun fetchHeroes() {
        viewModelScope.launch {

        }
    }
}