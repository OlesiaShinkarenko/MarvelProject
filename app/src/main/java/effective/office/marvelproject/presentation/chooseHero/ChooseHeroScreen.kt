package effective.office.marvelproject.presentation.chooseHero

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import effective.office.marvelproject.presentation.chooseHero.components.ChooseHeroHeader
import effective.office.marvelproject.presentation.chooseHero.components.ChooseHeroListUI
import effective.office.marvelproject.presentation.chooseHero.models.HeroesViewModel
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Padding

@Composable
fun ChooseHeroScreen(
    modifier: Modifier = Modifier,
    onCardHeroClicked: (Int) -> Unit,
    heroesViewModel: HeroesViewModel = hiltViewModel()
) {
    val heroesUiState = heroesViewModel.uiState.collectAsState()
    val heroes = heroesUiState.value.heroes.collectAsLazyPagingItems()
    Column(
        modifier = modifier.padding(
            if (AppTheme.ScreenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                Padding.vertical_8
            } else {
                Padding.vertical_24
            }
        ),
    ) {
        ChooseHeroHeader(
            modifier = Modifier.padding(
                if (AppTheme.ScreenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Padding.top_12
                } else {
                    Padding.top_40
                }
            )
        )
        ChooseHeroListUI(
            error = heroesUiState.value.error,
            listHero = heroes,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    if (AppTheme.ScreenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                        Padding.top_12
                    } else {
                        Padding.top_40
                    }
                ),
            onCardHeroClicked = onCardHeroClicked
        )
    }
}