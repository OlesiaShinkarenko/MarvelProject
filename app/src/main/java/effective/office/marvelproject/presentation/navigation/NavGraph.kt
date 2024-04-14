package effective.office.marvelproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

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
        descriptionComposable(
            onBackClicked = { navController.navigateUp() }
        )
    }
}