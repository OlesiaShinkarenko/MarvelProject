package effective.office.marvelproject.presentation.Hero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import effective.office.marvelproject.network.MarvelApi
import effective.office.marvelproject.network.model.toUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HeroViewModel : ViewModel() {
    private var _uiState = MutableStateFlow<HeroUiState>(HeroUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchHero(id: Int) {
        viewModelScope.launch {
            _uiState.value = try {
                val response = MarvelApi.retrofitService.getHero(
                    id = id
                )
                HeroUiState.Success(
                    response.data.results[0].toUI()
                )
            } catch (e: IOException) {
                HeroUiState.Error
            } catch (e: HttpException) {
                HeroUiState.Error
            }
        }
    }
}