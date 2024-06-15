package effective.office.marvelproject.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ApplySystemBarColors(darkIcons: Boolean) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !darkIcons,
            isNavigationBarContrastEnforced = false
        )
        systemUiController.isNavigationBarVisible = false
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = !darkIcons,
            navigationBarContrastEnforced = false,
        )
    }
}