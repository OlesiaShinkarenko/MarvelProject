package effective.office.marvelproject.presentation.chooseHero.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import effective.office.marvelproject.R

@Composable
fun MarvelLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.logo)
    )
}