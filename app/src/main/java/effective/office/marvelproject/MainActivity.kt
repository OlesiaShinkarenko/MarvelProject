package effective.office.marvelproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import effective.office.marvelproject.presentation.Hero.HeroScreen
import effective.office.marvelproject.presentation.chooseHero.ChooseHeroScreen
import effective.office.marvelproject.presentation.model.HeroUI
import effective.office.marvelproject.presentation.model.MarvelScreen
import effective.office.marvelproject.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }
}

@Composable
fun Screen(
    navController: NavHostController = rememberNavController()
) {
    val listHero = listOf(
        HeroUI(
            logo = "https://iili.io/JMnAfIV.png",
            name = stringResource(id = R.string.hero1),
            description = stringResource(id = R.string.description1)
        ),
        HeroUI(
            logo = "https://iili.io/JMnuDI2.png",
            name = stringResource(id = R.string.hero2),
            description = stringResource(id = R.string.description2),
        ),
        HeroUI(
            logo = "https://iili.io/JMnuyB9.png",
            name = stringResource(id = R.string.hero3),
            description = stringResource(id = R.string.description3)
        )
    )
    AppTheme {
        NavHost(
            navController = navController, startDestination = MarvelScreen.Start.name
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
                    onCardHeroClicked = { index ->
                        navController.navigate(
                            MarvelScreen.Description.name + index
                        )
                    },
                    listHero = listHero
                )
            }
            composable(
                route = MarvelScreen.Description.name + "{index}",
                arguments = listOf(
                    navArgument(name = "index") {
                        type = NavType.IntType
                    }
                )
            ) { index ->
                HeroScreen(
                    item = listHero[index.arguments?.getInt("index")!!],
                    modifier = Modifier.fillMaxSize(),
                    onBackClicked = { navController.navigateUp() }
                )
            }
        }
    }
}