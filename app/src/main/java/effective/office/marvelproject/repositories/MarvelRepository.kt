package effective.office.marvelproject.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.withTransaction
import effective.office.marvelproject.data.MarvelMediator
import effective.office.marvelproject.data.db.MarvelAppDatabase
import effective.office.marvelproject.data.network.either.Either
import effective.office.marvelproject.data.network.services.MarvelApi
import effective.office.marvelproject.mapper.toEntity
import effective.office.marvelproject.mapper.toUI
import effective.office.marvelproject.model.CharacterUI

class MarvelRepository(
    private val database: MarvelAppDatabase
) {
    private val characterDao = database.characterDao()

    @ExperimentalPagingApi
    fun getCharacterPage() = Pager(
        config = PagingConfig(
            pageSize = 100,
            prefetchDistance = (0.10 * 100).toInt()
        ),
        remoteMediator = MarvelMediator(database)
    ) {
        characterDao.getCharacters()
    }.flow

    suspend fun getCharacter(id: Int): CharacterUI {
        val character = characterDao.getCharacter(id)
        if (character == null) {
            val response = MarvelApi.retrofitService.getHero(id)
            when (response) {
                is Either.Success -> {
                    database.withTransaction {
                        val characterFromApi = response.value.data.results[0].toEntity()
                        characterDao.insert(characterFromApi)
                    }
                }

                is Either.Fail -> {

                }
            }
        }
        return characterDao.getCharacter(id)!!.toUI()
    }
}