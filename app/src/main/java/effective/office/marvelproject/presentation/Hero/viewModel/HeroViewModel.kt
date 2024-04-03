package effective.office.marvelproject.presentation.Hero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import effective.office.marvelproject.mapper.toUI
import effective.office.marvelproject.network.MarvelApi
import effective.office.marvelproject.network.either.Either
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeroViewModel : ViewModel() {
    private var _uiState = MutableStateFlow<HeroUiState>(HeroUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchHero(id: Int) {
        viewModelScope.launch {
           _uiState.value = HeroUiState.Loading
            val response = MarvelApi.retrofitService.getHero(id = id)
            _uiState.value = when (response) {
                is Either.Fail -> HeroUiState.Error(
                    response.value.description
                )

                is Either.Success -> HeroUiState.Success(
                    response.value.data.results[0].toUI()
                )
            }
        }


    }
}