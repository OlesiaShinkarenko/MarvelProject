package effective.office.marvelproject.presentation.Hero

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.Hero.viewModel.HeroUiState
import effective.office.marvelproject.presentation.Hero.viewModel.HeroViewModel
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Padding

@Composable
fun HeroScreen(
    modifier: Modifier = Modifier,
    id: Int,
    onBackClicked: () -> Unit,
    heroViewModel: HeroViewModel = viewModel()
) {
    heroViewModel.fetchHero(id = id)

    val heroUiState = heroViewModel.uiState.collectAsState().value
    Box(modifier = modifier) {
        when (heroUiState) {
            is HeroUiState.Success -> {
                val hero = heroUiState.hero
                val address = hero.thumbnail.path.replace("http", "https") + "." + hero.thumbnail.extension
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(address)
                        .size(Size.ORIGINAL)
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = hero.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(Padding.start_28_bottom_60)
                        .padding(Padding.end_28)
                ) {
                    Text(
                        text = hero.name,
                        style = AppTheme.typography.bold,
                        color = AppTheme.colors.mainColor
                    )
                    Text(
                        modifier = Modifier.padding(Padding.top_40),
                        text = hero.description,
                        style = AppTheme.typography.medium,
                        color = AppTheme.colors.descriptionColor
                    )
                }
            }
            is HeroUiState.Loading -> {}
            is HeroUiState.Error -> {}
        }
        IconButton(
            onClick = onBackClicked,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = AppTheme.colors.mainColor,
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
        /*
        HeroScreen(
            item = Heroe(
                logo = "https://iili.io/JMnAfIV.png",
                name = R.string.hero1,
                description = R.string.description1
            ),
            modifier = Modifier.fillMaxSize()
        ) {}
         */
    }
}