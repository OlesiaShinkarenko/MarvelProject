package effective.office.marvelproject.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import effective.office.marvelproject.mapper.toUI
import effective.office.marvelproject.model.HeroUI
import effective.office.marvelproject.network.MarvelApi
import effective.office.marvelproject.network.either.Either

class MarvelPagingSource : PagingSource<Int, HeroUI>() {
    override fun getRefreshKey(state: PagingState<Int, HeroUI>): Int? = state.anchorPosition


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HeroUI> {
        val page = params.key ?: 0
        return when (val response = MarvelApi.retrofitService.getHeroes(
            offset = page
        )) {
            is Either.Fail -> {
                LoadResult.Error(Throwable(message = response.value.description.toString()))
            }

            is Either.Success -> {
                LoadResult.Page(
                    data = response.value.data.results.map {
                        it.toUI()
                    },
                    prevKey = if (page == 1) null else page.minus(20),
                    nextKey = if (response.value.data.results.isEmpty()) null else page.plus(20)
                )
            }
        }
    }


}