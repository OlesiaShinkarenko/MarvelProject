package effective.office.marvelproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import effective.office.marvelproject.viewModel.HeroViewModel

@Composable
fun NavGraph(navController: NavHostController, heroViewModel: HeroViewModel = viewModel()) {

    val heroUiState by heroViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = MarvelScreen.Start.name
    ) {
        chooseComposable(
            listHero = heroUiState.listHeroes,
            onCardClicked = { index ->
                navController.navigate(
                    MarvelScreen.Description.name + index
                )
            }
        )
        descriptionComposable(
            listHeroes = heroUiState.listHeroes,
            onBackClicked = { navController.navigateUp() }
        )
    }
}