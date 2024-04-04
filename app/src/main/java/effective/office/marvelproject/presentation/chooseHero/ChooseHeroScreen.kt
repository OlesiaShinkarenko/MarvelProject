package effective.office.marvelproject.presentation.chooseHero

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
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
    val heroesUiState = heroesViewModel.uiState.collectAsLazyPagingItems()
    Column(
        modifier = modifier.padding(Padding.vertical_24),
    ) {

        ChooseHeroHeader(
            modifier = Modifier.fillMaxWidth()
        )
        when (heroesUiState.loadState.refresh) {
            is LoadState.Error -> {
                Toast.makeText(
                    context,
                    (heroesUiState.loadState.refresh as LoadState.Error).error.message?.let {
                        stringResource(
                            id = it.toInt()
                        )
                    }, Toast.LENGTH_SHORT
                ).show()
            }

            is LoadState.Loading -> {
                LoadingIndicator()
            }

            is LoadState.NotLoading -> {
                ChooseHeroListUI(
                    listHero = heroesUiState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Padding.top_40)
                        .padding(Padding.bottom_32),
                    onCardHeroClicked = onCardHeroClicked
                )
            }
        }

        when (heroesUiState.loadState.append) {
            is LoadState.Error -> {
                Toast.makeText(
                    context,
                    (heroesUiState.loadState.append as LoadState.Error).error.message?.let {
                        stringResource(
                            id = it.toInt()
                        )
                    }, Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }
}