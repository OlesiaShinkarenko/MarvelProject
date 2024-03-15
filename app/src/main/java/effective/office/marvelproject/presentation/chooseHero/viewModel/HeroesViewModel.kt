package effective.office.marvelproject.presentation.chooseHero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import effective.office.marvelproject.network.MarvelApi
import effective.office.marvelproject.network.model.toUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HeroesViewModel : ViewModel() {
    private var _uiState = MutableStateFlow<HeroesUiState>(HeroesUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        fetchHeroes()
    }

    private fun fetchHeroes() {
        viewModelScope.launch {
            _uiState.value = HeroesUiState.Loading
            _uiState.value = try {
                val response = MarvelApi.retrofitService.getHeroes()
                HeroesUiState.Success(
                    response.data.results.map {
                        it.toUI()
                    }
                )
            } catch (e: IOException) {
                HeroesUiState.Error
            } catch (e: HttpException) {
                HeroesUiState.Error
            }
        }
    }
}