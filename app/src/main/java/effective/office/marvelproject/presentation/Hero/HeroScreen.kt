package effective.office.marvelproject.presentation.Hero

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.model.HeroUI
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Padding

@Composable
fun HeroScreen(modifier: Modifier = Modifier, item: HeroUI) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.logo)
            .size(Size.ORIGINAL)
            .build()
    )
    Box(modifier = modifier) {
        Image(
            painter = painter,
            contentDescription = item.name,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(Padding.start_28_bottom_60)
                .padding(Padding.end_28)
        ) {
            Text(
                text = item.name,
                style = AppTheme.typography.bold,
                color = AppTheme.colors.mainColor
            )
            Text(
                modifier = Modifier.padding(Padding.top_24),
                text = item.description,
                style = AppTheme.typography.medium,
                color = AppTheme.colors.descriptionColor
            )
        }
    }
}

@Preview
@Composable
fun HeroScreenPreview() {
    Surface(
        color = AppTheme.colors.backgroundColor
    ) {
        HeroScreen(
            item = HeroUI(
                logo = "https://iili.io/JMnAfIV.png",
                name = stringResource(id = R.string.hero1),
                description = stringResource(id = R.string.description1)
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}