package effective.office.marvelproject.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import effective.office.marvelproject.presentation.components.ApplySystemBarColors
import effective.office.marvelproject.ui.theme.AppTheme.DarkColors

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        mainColor = DarkColors.main,
        descriptionColor = DarkColors.description,
        backgroundColor = DarkColors.background
    )
}

private val darkColors = CustomColors(
    mainColor = DarkColors.main,
    descriptionColor = DarkColors.description,
    backgroundColor = DarkColors.background
)

private val lightColors = CustomColors(
    mainColor = AppTheme.LightColors.main,
    descriptionColor = AppTheme.LightColors.description,
    backgroundColor = AppTheme.LightColors.background
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
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        darkColors
    } else {
        lightColors
    }
    val typography = CustomTypography(
        medium = AppTheme.TextStyle.Medium,

        
        bold = AppTheme.TextStyle.Bold
    )

    ApplySystemBarColors(darkTheme)
    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalCustomTypography provides typography,
        content = content
    )
}