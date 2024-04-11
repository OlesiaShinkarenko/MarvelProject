package effective.office.marvelproject.presentation.chooseHero

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import effective.office.marvelproject.R
import effective.office.marvelproject.data.db.models.CharacterEntity
import effective.office.marvelproject.model.CharacterUI
import effective.office.marvelproject.ui.theme.AppTheme
import effective.office.marvelproject.ui.theme.Padding
import effective.office.marvelproject.ui.theme.Shape
import effective.office.marvelproject.ui.theme.Size

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChooseHeroListUI(
    modifier: Modifier = Modifier,
    listHero: LazyPagingItems<CharacterEntity>,
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
        items(listHero.itemCount) {
            key(it) {
                HeroElement(
                    modifier = Modifier
                        .width(Size.size330)
                        .shadow(elevation = Size.size16, shape = Shape.shape10)
                        .clip(shape = Shape.shape10),
                    item = listHero[it]!!,
                    onCardHeroClicked = onCardHeroClicked
                )
            }
        }
    }

}

@Preview
@Composable
fun ChooseHeroListUIPreview() {
    val listHero = listOf(
        CharacterUI(
            id = 0,
            logo = "https://iili.io/JMnAfIV.png",
            name = stringResource(id = R.string.hero1),
            description = stringResource(id = R.string.description1)
        ),
        CharacterUI(
            id = 0,
            logo = "https://iili.io/JMnuDI2.png",
            name = stringResource(R.string.hero2),
            description = stringResource(R.string.description2),
        ),
        CharacterUI(
            id = 0,
            logo = "https://iili.io/JMnuyB9.png",
            name = stringResource(R.string.hero3),
            description = stringResource(R.string.description3)
        )
    )
    Surface(
        color = AppTheme.colors.backgroundColor
    ) {

    }
}