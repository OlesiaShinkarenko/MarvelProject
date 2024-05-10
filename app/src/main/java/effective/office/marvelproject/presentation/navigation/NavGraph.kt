package effective.office.marvelproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import effective.office.marvelproject.presentation.chooseHero.viewModel.HeroesViewModel
import effective.office.marvelproject.presentation.hero.viewModel.HeroViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    heroesViewModel: HeroesViewModel,
    heroViewModel: HeroViewModel
) {

    NavHost(
        navController = navController,
        startDestination = MarvelScreen.Start.name
    ) {
        chooseComposable(
            heroesViewModel = heroesViewModel,
            onCardClicked = { index ->
                navController.navigate(
                    MarvelScreen.Description.name + index
                )
            }
        )
        descriptionComposable(
            onBackClicked = { navController.navigateUp() },
            heroViewModel = heroViewModel
        )
    }
}