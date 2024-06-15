package effective.office.marvelproject.presentation.hero.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import effective.office.marvelproject.R
import effective.office.marvelproject.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroScreenTopAppBar(onBackClicked: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent,
            navigationIconContentColor = AppTheme.colors.mainColor,
            actionIconContentColor = AppTheme.colors.mainColor
        ),
        title = {},
        navigationIcon = {
            IconButton(
                onClick = onBackClicked,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                )
            }
        }
    )
}