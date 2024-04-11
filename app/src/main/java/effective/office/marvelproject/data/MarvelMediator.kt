package effective.office.marvelproject.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import effective.office.marvelproject.data.db.MarvelAppDatabase
import effective.office.marvelproject.data.db.models.CharacterEntity
import effective.office.marvelproject.data.db.models.RemoteKey
import effective.office.marvelproject.data.network.either.Either
import effective.office.marvelproject.data.network.services.MarvelApi
import effective.office.marvelproject.mapper.toEntity

@OptIn(ExperimentalPagingApi::class)
class MarvelMediator(private val database: MarvelAppDatabase) :
    RemoteMediator<Int, CharacterEntity>() {

    private val remoteKeyDao = database.remoteKeyDao()
    private val characterDao = database.characterDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND ->
                return MediatorResult.Success(endOfPaginationReached = true)

            LoadType.APPEND -> {
                val remoteKey = database.withTransaction {
                    remoteKeyDao.remoteKeyById("all")
                }
                if (remoteKey.nextKey == null) {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                remoteKey.nextKey
            }
        }

        val response = MarvelApi.retrofitService.getHeroes(
            limit = state.config.pageSize,
            offset = loadKey
        )

        when (response) {
            is Either.Success -> {
                val data = response.value.data.results.map {
                    it.toEntity()
                }
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        characterDao.clear()
                        remoteKeyDao.remoteKeyById("all")
                    }
                    characterDao.insertAll(data)
                    remoteKeyDao.insert(
                        RemoteKey("all", loadKey + 100)
                    )
                }

                return MediatorResult.Success(
                    endOfPaginationReached = data.isEmpty()
                )
            }

            is Either.Fail -> {
                return MediatorResult.Error(Throwable(message = response.value.description.toString()))
            }
        }
    }
}