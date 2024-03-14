package effective.office.marvelproject.presentation.chooseHero

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Scale
import coil.util.DebugLogger
import effective.office.marvelproject.model.Hero
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Padding
import effective.office.marvelproject.ui.theme.Size
import effective.office.marvelproject.network.model.Character

@Composable
fun HeroElement(
    modifier: Modifier = Modifier,
    item: Character,
    onCardHeroClicked: () -> Unit
) {

    val address = item.thumbnail.path.replace("http", "https") + "." + item.thumbnail.extension
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(address)
            .apply(
                block = fun ImageRequest.Builder.() {
                    scale(Scale.FILL)
                }
            )
            .build()
    )

    Box(modifier = modifier.clickable { onCardHeroClicked() }
    ) {

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