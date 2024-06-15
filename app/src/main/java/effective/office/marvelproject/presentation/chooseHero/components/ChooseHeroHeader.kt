package effective.office.marvelproject.presentation.chooseHero.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import effective.office.marvelproject.R
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Size

@Composable
fun ChooseHeroHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MarvelLogo(
            modifier = Modifier.width(
                Size.size150
            )
        )
        Text(
            text = stringResource(id = R.string.choose),
            style = AppTheme.typography.bold,
            color = AppTheme.colors.mainColor,
            modifier = modifier
        )
    }
}
