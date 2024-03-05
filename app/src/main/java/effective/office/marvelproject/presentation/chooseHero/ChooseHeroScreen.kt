package effective.office.marvelproject.presentation.chooseHero

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import effective.office.marvelproject.presentation.model.HeroUI
import effective.office.marvelproject.ui.theme.Padding

@Composable
fun ChooseHeroScreen(
    modifier: Modifier = Modifier,
    onCardHeroClicked: (Int) -> Unit,
    listHero: List<HeroUI>
) {
    Column(
        modifier = modifier.padding(Padding.vertical_24),
    ) {
        ChooseHeroHeader(
            modifier = Modifier.fillMaxWidth()
        )
        ChooseHeroListUI(
            listHero = listHero,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.top_40)
                .padding(Padding.bottom_32),
            onCardHeroClicked = onCardHeroClicked
        )
    }
}