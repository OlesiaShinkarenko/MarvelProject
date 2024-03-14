package effective.office.marvelproject.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import effective.office.marvelproject.network.MarvelApi
import kotlinx.coroutines.Dispatchers
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
                    response.data.results
                )
            } catch (e: IOException) {
                Log.d("err",e.message.toString())
                HeroesUiState.Error
            }
            catch (e: HttpException){
                Log.d("er", e.response()?.message().toString())
                HeroesUiState.Error
            }
        }
    }
}