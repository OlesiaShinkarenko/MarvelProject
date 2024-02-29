package effective.office.marvelproject.presentation.chooseHero

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import effective.office.marvelproject.ui.theme.Padding
import effective.office.marvelproject.ui.theme.Size

@Composable
fun ChooseHeroScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(Padding.vertical_24_horizontal_16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MarvelLogo(
            modifier = Modifier.width(
                Size.size150
            )
        )
    }
}