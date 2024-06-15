package effective.office.marvelproject.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import effective.office.marvelproject.presentation.hero.HeroScreen
import effective.office.marvelproject.utils.Constants.Companion.MY_URI

fun NavGraphBuilder.descriptionComposable(
    onBackClicked: () -> Unit,
) {
    composable(
        route = MarvelScreen.Description.name + "{index}",
        arguments = listOf(
            navArgument(name = "index") {
                type = NavType.IntType
            }
        ),
        deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/index={index}" })
    ) { index ->
        HeroScreen(
            id = index.arguments?.getInt("index")!!,
            modifier = Modifier.fillMaxSize(),
            onBackClicked = onBackClicked,
        )
    }
}