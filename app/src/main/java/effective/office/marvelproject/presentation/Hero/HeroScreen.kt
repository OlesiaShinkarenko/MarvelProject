package effective.office.marvelproject.presentation.Hero

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import effective.office.marvelproject.R
import effective.office.marvelproject.model.HeroUI
import effective.office.marvelproject.presentation.Hero.viewModel.HeroUiState
import effective.office.marvelproject.presentation.Hero.viewModel.HeroViewModel
import effective.office.marvelproject.presentation.components.LoadingIndicator
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

    val context = LocalContext.current

    when (val heroUiState = heroViewModel.uiState.collectAsState().value) {
        is HeroUiState.Success -> {
            HeroContentScreen(
                modifier = modifier,
                heroUiState.hero,
            )
        }

        is HeroUiState.Loading -> {
            LoadingIndicator()
        }

        is HeroUiState.Error -> {
            Toast.makeText(context, heroUiState.error, Toast.LENGTH_SHORT).show()
        }
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

@Composable
fun HeroContentScreen(modifier: Modifier, hero: HeroUI) {
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
}
