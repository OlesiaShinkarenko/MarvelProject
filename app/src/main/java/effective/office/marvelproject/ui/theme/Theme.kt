package effective.office.marvelproject.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import effective.office.marvelproject.ui.theme.AppTheme.TextColors

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        mainColor = TextColors.main,
        descriptionColor = TextColors.description
    )
}

private val darkColors = CustomColors(
    mainColor = TextColors.main,
    descriptionColor = TextColors.description
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = darkColors

    CompositionLocalProvider(
        LocalCustomColors provides colors,
        content = content
    )
}