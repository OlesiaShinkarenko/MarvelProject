package effective.office.marvelproject.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp


object AppTheme {
    val ScreenOrientation
        @Composable
        get() = LocalConfiguration.current.orientation
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current

    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current

    object DarkColors {
        val main = Color(0xFFFFFFFF)
        val description = Color(0xFFEEF2FB)
        val background = Color(0xFF2b272b)
    }

    object LightColors {
        val main = Color(0xFF000000)
        val description = Color(0xFF241D24)
        val background = Color(0xFFECEBEC)
    }

    object TextStyle {
        val Medium = TextStyle(
            fontFamily = FontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            lineHeightStyle = LineHeightStyle(
                LineHeightStyle.Alignment.Proportional,
                LineHeightStyle.Trim.None
            )
        )
        val Bold = TextStyle(
            fontFamily = FontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            lineHeightStyle = LineHeightStyle(
                LineHeightStyle.Alignment.Proportional,
                LineHeightStyle.Trim.None
            )
        )
    }
}