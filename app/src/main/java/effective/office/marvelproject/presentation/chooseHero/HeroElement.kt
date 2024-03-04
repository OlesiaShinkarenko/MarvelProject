package effective.office.marvelproject.presentation.chooseHero

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import effective.office.marvelproject.presentation.model.HeroUI
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Padding
import effective.office.marvelproject.ui.theme.Size

@Composable
fun HeroElement(
    modifier: Modifier = Modifier,
    item: HeroUI,
    onCardHeroClicked: () -> Unit
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.logo)
            .size(coil.size.Size.ORIGINAL)
            .build()
    )
    Box(modifier = modifier.clickable { onCardHeroClicked() }
    ) {
        Image(
            painter = painter,
            modifier = Modifier.width(
                Size.size330
            ),
            contentDescription = item.name,
            contentScale = ContentScale.FillBounds,
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(Padding.start_28_bottom_60),
            text = item.name,
            style = AppTheme.typography.bold,
            color = AppTheme.colors.mainColor
        )
    }
}