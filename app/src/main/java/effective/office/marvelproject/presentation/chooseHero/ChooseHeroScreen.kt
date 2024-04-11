package effective.office.marvelproject.presentation.chooseHero

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import effective.office.marvelproject.presentation.chooseHero.viewModel.HeroesViewModel
import effective.office.marvelproject.presentation.chooseHero.viewModel.HeroesViewModel.Companion.Factory
import effective.office.marvelproject.ui.theme.Padding

@Composable
fun ChooseHeroScreen(
    modifier: Modifier = Modifier,
    onCardHeroClicked: (Int) -> Unit,
    heroesViewModel: HeroesViewModel = viewModel(
        factory = Factory
    )
) {
    val context = LocalContext.current
    val heroesUiState = heroesViewModel.characters.collectAsLazyPagingItems()
    Column(
        modifier = modifier.padding(Padding.vertical_24),
    ) {

        ChooseHeroHeader(
            modifier = Modifier.fillMaxWidth()
        )



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