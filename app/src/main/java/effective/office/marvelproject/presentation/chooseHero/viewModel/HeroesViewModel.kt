package effective.office.marvelproject.presentation.chooseHero.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import effective.office.marvelproject.MarvelApplication
import effective.office.marvelproject.domain.repositories.MarvelRepository

class HeroesViewModel(private val repository: MarvelRepository) : ViewModel() {
    @OptIn(ExperimentalPagingApi::class)
    val characters = repository.getCharacterPage().cachedIn(
        viewModelScope
    )

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