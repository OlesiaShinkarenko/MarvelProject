package effective.office.marvelproject.presentation.chooseHero

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.model.HeroUI
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Padding
import effective.office.marvelproject.ui.theme.Shape
import effective.office.marvelproject.ui.theme.Size

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChooseHeroListUI(modifier: Modifier = Modifier, listHero: List<HeroUI>) {
    val state = rememberLazyListState()
    val snappingLayout = remember(state) {
        SnapLayoutInfoProvider(
            state,
            positionInLayout = SnapPositionInLayout.CenterToCenter
        )
    }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)
    LazyRow(
        contentPadding = Padding.horizontal_30,
        modifier = modifier,
        state = state,
        horizontalArrangement = Arrangement.spacedBy(Size.size20),
        flingBehavior = flingBehavior,
    ) {
        items(listHero) { item ->
            HeroElement(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = Shape.shape10)
                    .shadow(elevation = Size.size4, shape = Shape.shape10),
                item = item
            )
        }
    }
}

@Preview
@Composable
fun ChooseHeroListUIPreview() {
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
    Surface(
        color = AppTheme.colors.backgroundColor
    ) {
        ChooseHeroListUI(
            listHero = listHero,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.top_24)
        )
    }
}