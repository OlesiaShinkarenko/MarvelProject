package effective.office.marvelproject.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import effective.office.marvelproject.data.local.MarvelAppDatabase
import effective.office.marvelproject.data.network.Either
import effective.office.marvelproject.data.remote.model.mappers.toEntity
import effective.office.marvelproject.data.remote.services.MarvelApiService
import effective.office.marvelproject.domain.model.CharacterEntity

class MarvelPagingSource(
    private val database: MarvelAppDatabase,
    private val marvelApiService: MarvelApiService
) :
    PagingSource<Int, CharacterEntity>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>) = state.anchorPosition


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        val page = params.key ?: 0
        return when (val response = marvelApiService.getHeroes(
            offset = page
        )) {
            is Either.Fail -> {
                LoadResult.Error(Throwable(message = response.value.description.toString()))
            }

            is Either.Success -> {
                database.withTransaction {
                    if (page == 0) {
                        database.characterDao().clear()
                    }
                    database.characterDao().insertAll(
                        response.value.data.results.map {
                            it.toEntity()
                        }
                    )
                }
                LoadResult.Page(
                    data = response.value.data.results.map {
                        it.toEntity()
                    },
                    prevKey = if (page == 0) null else page.minus(20),
                    nextKey = if (response.value.data.results.isEmpty()) null else page.plus(20)
                )
            }
        }
    }
}