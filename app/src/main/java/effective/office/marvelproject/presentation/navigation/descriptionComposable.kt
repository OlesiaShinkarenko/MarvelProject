package effective.office.marvelproject.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import effective.office.marvelproject.presentation.Hero.HeroScreen
import effective.office.marvelproject.model.Hero

fun NavGraphBuilder.descriptionComposable(
    listHeroes: List<Hero>,
    onBackClicked: () -> Unit
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
            item = listHeroes[index.arguments?.getInt("index")!!],
            modifier = Modifier.fillMaxSize(),
            onBackClicked = onBackClicked
        )
    }
}