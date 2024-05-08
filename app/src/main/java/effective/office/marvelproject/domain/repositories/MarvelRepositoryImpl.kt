package effective.office.marvelproject.domain.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import androidx.room.withTransaction
import effective.office.marvelproject.data.db.MarvelAppDatabase
import effective.office.marvelproject.data.mapper.toEntity
import effective.office.marvelproject.data.mapper.toUI
import effective.office.marvelproject.data.network.either.Either
import effective.office.marvelproject.data.network.model.ErrorResponse
import effective.office.marvelproject.data.network.paging.MarvelPagingSource
import effective.office.marvelproject.data.network.services.MarvelApiService
import effective.office.marvelproject.presentation.model.CharacterUI
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
            )
        ) {
            MarvelPagingSource(database, marvelApiService)
        }.flow.map { data ->
            data.map {
                it.toUI()
            }
        }


    override suspend fun getCharacter(id: Int): Either<ErrorResponse, CharacterUI> {
        val character = characterDao.getCharacter(id)
        if (character == null) {
            when (val remote = marvelApiService.getHero(id)) {
                is Either.Success -> {
                    val data = remote.value.data.results[0].toEntity()
                    database.withTransaction {
                        characterDao.insert(data)
                    }
                    return Either.Success(data.toUI())
                }

                is Either.Fail -> {
                    return Either.Fail(remote.value)
                }
            }
        } else {
            return Either.Success(character.toUI())
        }
    }
}
