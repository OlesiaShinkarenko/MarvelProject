package effective.office.marvelproject.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import androidx.room.withTransaction
import effective.office.marvelproject.data.local.MarvelAppDatabase
import effective.office.marvelproject.data.network.Either
import effective.office.marvelproject.data.remote.MarvelPagingSource
import effective.office.marvelproject.data.remote.model.ErrorResponse
import effective.office.marvelproject.data.remote.model.mappers.toEntity
import effective.office.marvelproject.data.remote.services.MarvelApiService
import effective.office.marvelproject.domain.repository.MarvelRepository
import effective.office.marvelproject.presentation.models.CharacterUI
import effective.office.marvelproject.presentation.models.mappers.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImpl @Inject constructor(
    private val database: MarvelAppDatabase,
    private val marvelApiService: MarvelApiService

) : MarvelRepository {
    private val characterDao = database.characterDao()

    private val PAGE_SIZE = 20

    @ExperimentalPagingApi
    override fun getCharacterPage() =
        Pager(
            config = PagingConfig(
                PAGE_SIZE,
                enablePlaceholders = true
            ),
        ) {
            MarvelPagingSource(database, marvelApiService)
        }.flow.map { data ->
            data.map {
                it.toUI()
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun getCharacter(id: Int): Flow<Either<ErrorResponse, CharacterUI>> = flow {
        val character = characterDao.getCharacter(id)
        if (character == null) {
            when (val remote = marvelApiService.getHero(id)) {
                is Either.Success -> {
                    val data = remote.value.data.results[0].toEntity()
                    database.withTransaction {
                        characterDao.insert(data)
                    }
                    emit(Either.Success(data.toUI()))
                }

                is Either.Fail -> {
                    emit(Either.Fail(remote.value))
                }
            }
        } else {
            emit(Either.Success(character.toUI()))
        }
    }.flowOn(Dispatchers.IO)
}
