package effective.office.marvelproject.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.chooseHero.ChooseHeroScreen


fun NavGraphBuilder.chooseComposable(
    onCardClicked: (Int) -> Unit,
) {
    composable(route = MarvelScreen.Start.name) {
        ChooseHeroScreen(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.Crop
                ),
            onCardHeroClicked = onCardClicked
        )
    }
}