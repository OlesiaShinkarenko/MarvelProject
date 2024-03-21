package effective.office.marvelproject.presentation.chooseHero

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import effective.office.marvelproject.presentation.chooseHero.viewModel.HeroesUiState
import effective.office.marvelproject.presentation.chooseHero.viewModel.HeroesViewModel
import effective.office.marvelproject.presentation.components.LoadingIndicator
import effective.office.marvelproject.ui.theme.Padding

@Composable
fun ChooseHeroScreen(
    modifier: Modifier = Modifier,
    onCardHeroClicked: (Int) -> Unit,
    heroesViewModel: HeroesViewModel = viewModel()
) {
    val context = LocalContext.current
    val heroesUiState = heroesViewModel.uiState.collectAsState().value
    Column(
        modifier = modifier.padding(Padding.vertical_24),
    ) {
        ChooseHeroHeader(
            modifier = Modifier.fillMaxWidth()
        )

        when (heroesUiState) {
            is HeroesUiState.Success -> ChooseHeroListUI(
                listHero = heroesUiState.listHeroes,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Padding.top_40)
                    .padding(Padding.bottom_32),
                onCardHeroClicked = onCardHeroClicked
            )

            is HeroesUiState.Error -> {
                Toast.makeText(context, stringResource(id = heroesUiState.message), Toast.LENGTH_SHORT).show()
            }

            is HeroesUiState.Loading -> {
                LoadingIndicator()
            }
        }

    }
}