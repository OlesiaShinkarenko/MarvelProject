package effective.office.marvelproject.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import effective.office.marvelproject.ui.theme.AppTheme.TextColors

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        mainColor = TextColors.main,
        descriptionColor = TextColors.description,
        backgroundColor = TextColors.background
    )
}

private val darkColors = CustomColors(
    mainColor = TextColors.main,
    descriptionColor = TextColors.description,
    backgroundColor = TextColors.background
)

val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography(
        medium = AppTheme.TextStyle.Medium,
        bold = AppTheme.TextStyle.Bold
    )
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = darkColors
    val typography = CustomTypography(
        medium = AppTheme.TextStyle.Medium,
        bold = AppTheme.TextStyle.Bold
    )

    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalCustomTypography provides typography,
        content = content
    )
}