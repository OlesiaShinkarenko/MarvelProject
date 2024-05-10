package effective.office.marvelproject.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import effective.office.marvelproject.presentation.hero.HeroScreen
import effective.office.marvelproject.presentation.hero.viewModel.HeroViewModel

fun NavGraphBuilder.descriptionComposable(
    onBackClicked: () -> Unit,
    heroViewModel: HeroViewModel
) {
    composable(
        route = MarvelScreen.Description.name + "{index}",
        arguments = listOf(
            navArgument(name = "index") {
                type = NavType.IntType
            }
        )
    ) { index ->
        HeroScreen(
            id = index.arguments?.getInt("index")!!,
            modifier = Modifier.fillMaxSize(),
            onBackClicked = onBackClicked,
            heroViewModel = heroViewModel
        )
    }
}