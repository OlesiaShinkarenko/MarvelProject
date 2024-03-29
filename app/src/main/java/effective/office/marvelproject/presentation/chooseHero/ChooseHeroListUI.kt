package effective.office.marvelproject.presentation.chooseHero

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import effective.office.marvelproject.R
import effective.office.marvelproject.presentation.model.Hero
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Padding
import effective.office.marvelproject.ui.theme.Shape
import effective.office.marvelproject.ui.theme.Size

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChooseHeroListUI(
    modifier: Modifier = Modifier,
    listHero: List<Hero>,
    onCardHeroClicked: (Int) -> Unit
) {
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
        itemsIndexed(listHero) { index, item ->
            HeroElement(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(elevation = Size.size16, shape = Shape.shape10)
                    .clip(shape = Shape.shape10),
                item = item,
                onCardHeroClicked = { onCardHeroClicked(index) }
            )
        }
    }
}

@Preview
@Composable
fun ChooseHeroListUIPreview() {
    val listHero = listOf(
        Hero(
            logo = "https://iili.io/JMnAfIV.png",
            name = R.string.hero1,
            description = R.string.description1
        ),
        Hero(
            logo = "https://iili.io/JMnuDI2.png",
            name = R.string.hero2,
            description = R.string.description2,
        ),
        Hero(
            logo = "https://iili.io/JMnuyB9.png",
            name = R.string.hero3,
            description = R.string.description3
        )
    )
    Surface(
        color = AppTheme.colors.backgroundColor
    ) {
        ChooseHeroListUI(
            listHero = listHero,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.top_40),
            onCardHeroClicked = {}
        )
    }
}