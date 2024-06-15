package effective.office.marvelproject.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import effective.office.marvelproject.presentation.chooseHero.ChooseHeroScreen


fun NavGraphBuilder.chooseComposable(
    onCardClicked: (Int) -> Unit,
) {
    composable(route = MarvelScreen.Start.name) {
        ChooseHeroScreen(
            modifier = Modifier
                .fillMaxSize(),
            onCardHeroClicked = onCardClicked
        )
    }
}