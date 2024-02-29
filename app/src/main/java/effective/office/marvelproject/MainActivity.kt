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
import effective.office.marvelproject.presentation.chooseHero.ChooseHeroScreen
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
fun Screen() {
    AppTheme {
        ChooseHeroScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = AppTheme.colors.backgroundColor
                )
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.Crop
                )
        )
    }
}