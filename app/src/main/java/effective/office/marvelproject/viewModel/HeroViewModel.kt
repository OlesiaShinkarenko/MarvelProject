package effective.office.marvelproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.model.Hero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeroViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HeroUiState())

    val uiState: StateFlow<HeroUiState> = _uiState.asStateFlow()

    init {
        fetchHeroes()
    }

    private fun fetchHeroes() {
        viewModelScope.launch {
            _uiState.value = HeroUiState(
                listOf(
                    Hero(
                        logo = "https://iili.io/JMnAfIV.png",
                        name = R.string.hero1,
                        description = R.string.description1
                    ),
                    Hero(
                        logo = "https://iili.io/JMnuDI2.png",
                        name = R.string.hero2,
                        description = R.string.description2,
                    ),
                    Hero(
                        logo = "https://iili.io/JMnuyB9.png",
                        name = R.string.hero3,
                        description = R.string.description3
                    )
                )
            )
        }
    }
}