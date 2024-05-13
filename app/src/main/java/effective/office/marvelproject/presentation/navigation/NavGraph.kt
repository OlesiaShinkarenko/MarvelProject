package effective.office.marvelproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MarvelScreen.Start.name
    ) {
        chooseComposable(
            onCardClicked = { index ->
                navController.navigate(
                    MarvelScreen.Description.name + index
                )
            }
        )
        descriptionComposable(
            onBackClicked = { navController.navigateUp() },
        )
    }
}