package effective.office.marvelproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import effective.office.marvelproject.presentation.navigation.NavGraph
import effective.office.marvelproject.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    color = AppTheme.colors.backgroundColor
                ) {
                    NavGraph()
                }
            }
        }
    }
}