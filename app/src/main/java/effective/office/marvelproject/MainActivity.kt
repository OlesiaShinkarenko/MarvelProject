package effective.office.marvelproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import effective.office.marvelproject.presentation.Hero.viewModel.HeroViewModel
import effective.office.marvelproject.presentation.chooseHero.viewModel.HeroesViewModel
import effective.office.marvelproject.presentation.navigation.NavGraph
import effective.office.marvelproject.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val heroesViewModel: HeroesViewModel by viewModels<HeroesViewModel>()
            val heroViewModel: HeroViewModel by viewModels<HeroViewModel>()

            AppTheme {
                Surface(
                    color = AppTheme.colors.backgroundColor
                ) {
                    val navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        heroesViewModel = heroesViewModel,
                        heroViewModel = heroViewModel
                    )
                }
            }
        }
    }
}