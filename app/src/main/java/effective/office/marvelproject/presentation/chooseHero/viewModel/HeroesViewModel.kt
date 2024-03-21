package effective.office.marvelproject.presentation.chooseHero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import effective.office.marvelproject.mapper.toUI
import effective.office.marvelproject.network.MarvelApi
import effective.office.marvelproject.network.either.Either
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeroesViewModel : ViewModel() {
    private var _uiState = MutableStateFlow<HeroesUiState>(HeroesUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        fetchHeroes()
    }

    private fun fetchHeroes() {
        viewModelScope.launch {
            _uiState.value = HeroesUiState.Loading
            val response = MarvelApi.retrofitService.getHeroes()
            _uiState.value =
                when (response) {
                    is Either.Fail -> HeroesUiState.Error(response.value.description)
                    is Either.Success -> HeroesUiState.Success(response.value.data.results.map {
                        it.toUI()
                    })
                }
        }
    }
}