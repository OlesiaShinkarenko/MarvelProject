package effective.office.marvelproject.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.chooseHero.ChooseHeroScreen
import effective.office.marvelproject.model.Hero
import effective.office.marvelproject.ui.theme.AppTheme


fun NavGraphBuilder.chooseComposable(
    listHero: List<Hero>,
    onCardClicked: (Int) -> Unit
) {
    composable(route = MarvelScreen.Start.name) {
        ChooseHeroScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppTheme.colors.backgroundColor
                )
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.Crop
                ),
            onCardHeroClicked = onCardClicked,
            listHero = listHero
        )
    }
}