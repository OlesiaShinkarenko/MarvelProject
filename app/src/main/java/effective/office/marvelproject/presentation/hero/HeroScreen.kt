package effective.office.marvelproject.presentation.hero

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import effective.office.marvelproject.presentation.components.LoadingIndicator
import effective.office.marvelproject.presentation.hero.components.HeroScreenTopAppBar
import effective.office.marvelproject.presentation.models.CharacterUI
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Padding

@Composable
fun HeroScreen(
    modifier: Modifier = Modifier,
    id: Int,
    onBackClicked: () -> Unit,
    heroViewModel: HeroViewModel = hiltViewModel()
) {
    val heroUiState = heroViewModel.state.collectAsState()
    val isLoading = heroUiState.value.isLoading
    val context = LocalContext.current

    LaunchedEffect(heroViewModel.state) {
        heroViewModel.fetchHero(id = id)
    }
    if (isLoading) {
        LoadingIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(
                    Alignment.CenterHorizontally
                )
                .wrapContentHeight(
                    Alignment.CenterVertically
                )
        )
    }
    heroUiState.value.hero?.let {
        HeroContentScreen(
            modifier = modifier,
            hero = it
        )
    }
    heroUiState.value.error?.let {
        Toast.makeText(
            context,
            stringResource(id = it),
            Toast.LENGTH_SHORT
        ).show()
    }
    HeroScreenTopAppBar(
        onBackClicked
    )
}

@Composable
private fun HeroContentScreen(modifier: Modifier = Modifier, hero: CharacterUI) {
    Box(modifier = modifier) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(hero.logo)
                .size(Size.ORIGINAL)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = hero.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(Padding.start_28_bottom_60)
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
}
