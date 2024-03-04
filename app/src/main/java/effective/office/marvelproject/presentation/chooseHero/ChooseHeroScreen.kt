package effective.office.marvelproject.presentation.chooseHero

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.model.HeroUI
import effective.office.marvelproject.ui.theme.Padding

@Composable
fun ChooseHeroScreen(modifier: Modifier = Modifier) {
    val listHero = listOf(
        HeroUI(
            logo = "https://iili.io/JMnAfIV.png",
            name = stringResource(id = R.string.hero1),
            description = stringResource(id = R.string.description1)
        ),
        HeroUI(
            logo = "https://iili.io/JMnuDI2.png",
            name = stringResource(id = R.string.hero2),
            description = stringResource(id = R.string.description2),
        ),
        HeroUI(
            logo = "https://iili.io/JMnuyB9.png",
            name = stringResource(id = R.string.hero3),
            description = stringResource(id = R.string.description3)
        )
    )



    Column(
        modifier = modifier.padding(Padding.vertical_24),
    ) {
        ChooseHeroHeader(
            modifier = Modifier.fillMaxWidth()
        )
        ChooseHeroListUI(
            listHero = listHero, modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.top_24)
        )
    }
}