package effective.office.marvelproject.presentation.chooseHero

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import effective.office.marvelproject.data.db.models.CharacterEntity
import effective.office.marvelproject.presentation.components.LoadingIndicator
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

    val context = LocalContext.current

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
                listHero[it]?.let { it1 ->
                    HeroElement(
                        modifier = Modifier
                            .width(Size.size330)
                            .shadow(elevation = Size.size16, shape = Shape.shape10)
                            .clip(shape = Shape.shape10),
                        item = it1,
                        onCardHeroClicked = onCardHeroClicked
                    )
                }
            }
        }
        loadState(listHero.loadState, context = context)
    }
}

fun LazyListScope.loadState(loadState: CombinedLoadStates, context: Context) {
    loadState.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                item {
                    LoadingIndicator(
                        Modifier
                            .fillMaxSize()
                            .wrapContentWidth(
                                Alignment.CenterHorizontally
                            )
                            .wrapContentHeight(
                                Alignment.CenterVertically
                            )
                    )
                }
            }

            loadState.append is LoadState.Loading -> {
                item {
                    LoadingIndicator(
                        Modifier
                            .fillMaxSize()
                            .wrapContentWidth(
                                Alignment.CenterHorizontally
                            )
                            .wrapContentHeight(
                                Alignment.CenterVertically
                            )
                    )
                }
            }

            loadState.refresh is LoadState.Error -> {
                val error = (loadState.refresh as LoadState.Error).error.message?.toInt()
                item {
                    ShowToast(context, error)
                }
            }

            loadState.append is LoadState.Error -> {
                val error = (loadState.append as LoadState.Error).error.message?.toInt()
                item {
                    ShowToast(context, error)
                }
            }
        }
    }
}

@Composable
fun ShowToast(context: Context, error: Int?) {
    error?.let {
        Toast.makeText(context, stringResource(id = error), Toast.LENGTH_SHORT)
            .show()
    }
}

