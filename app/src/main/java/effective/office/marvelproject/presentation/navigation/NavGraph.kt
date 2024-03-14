package effective.office.marvelproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import effective.office.marvelproject.viewModel.HeroesViewModel

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = MarvelScreen.Start.name
    ) {
        chooseComposable { index ->
            navController.navigate(
                MarvelScreen.Description.name + index
            )
        }
        /*
        descriptionComposable(
            listHeroes = heroUiState.listHeroes,
            onBackClicked = { navController.navigateUp() }
        )
         */
    }
}