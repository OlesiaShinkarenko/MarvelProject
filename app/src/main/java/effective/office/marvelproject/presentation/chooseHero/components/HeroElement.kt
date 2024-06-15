package effective.office.marvelproject.presentation.chooseHero.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import effective.office.marvelproject.presentation.models.CharacterUI
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Elevation
import effective.office.marvelproject.ui.theme.Padding

@Composable
fun HeroElement(
    modifier: Modifier = Modifier,
    item: CharacterUI,
    onCardHeroClicked: (Int) -> Unit
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.logo)
            .apply(
                block = fun ImageRequest.Builder.() {
                    scale(Scale.FILL)
                }
            )
            .build()
    )

    Card(
        modifier = modifier.clickable {
            onCardHeroClicked(item.id)
        },
        elevation = Elevation.elevation10
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painter,
                modifier = Modifier.fillMaxHeight(),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(Padding.start_28_bottom_60)
                    .padding(Padding.end_28),
                text = item.name,
                style = AppTheme.typography.bold,
                color = AppTheme.colors.mainColor
            )
        }

    }
}