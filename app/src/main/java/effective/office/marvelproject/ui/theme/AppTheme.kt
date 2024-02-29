package effective.office.marvelproject.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


object AppTheme {

    val colors: CustomColors
    @Composable
    get() = LocalCustomColors.current
    object TextColors{
        val main = Color(0xFFFFFFFF)
        val description = Color(0xFFEEF2FB)
    }
}