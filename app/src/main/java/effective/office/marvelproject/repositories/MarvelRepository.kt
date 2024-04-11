package effective.office.marvelproject.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.withTransaction
import effective.office.marvelproject.data.MarvelMediator
import effective.office.marvelproject.data.db.MarvelAppDatabase
import effective.office.marvelproject.data.network.either.Either
import effective.office.marvelproject.data.network.model.ErrorResponse
import effective.office.marvelproject.data.network.services.MarvelApi
import effective.office.marvelproject.mapper.toEntity
import effective.office.marvelproject.mapper.toUI
import effective.office.marvelproject.model.CharacterUI

class MarvelRepository(
    private val database: MarvelAppDatabase
) {
    private val characterDao = database.characterDao()

    private val PAGE_SIZE = 20
    @ExperimentalPagingApi
    fun getCharacterPage() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = 1
        ),
        remoteMediator = MarvelMediator(database)
    ) {
        characterDao.getCharacters()
    }.flow

    suspend fun getCharacter(id: Int): Either<ErrorResponse, CharacterUI> {
        characterDao.getCharacter(id) ?: when (val response =
            MarvelApi.retrofitService.getHero(id)) {
            is Either.Success -> {
                database.withTransaction {
                    val characterFromApi = response.value.data.results[0].toEntity()
                    characterDao.insert(characterFromApi)
                }
            }

            is Either.Fail -> {
                return Either.fail(response.value)
            }
        }
        return Either.success(characterDao.getCharacter(id)!!.toUI())
    }
}