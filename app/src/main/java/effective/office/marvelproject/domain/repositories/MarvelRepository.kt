package effective.office.marvelproject.domain.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import effective.office.marvelproject.R
import effective.office.marvelproject.data.db.MarvelAppDatabase
import effective.office.marvelproject.data.mapper.toUI
import effective.office.marvelproject.data.network.either.Either
import effective.office.marvelproject.data.network.model.ErrorResponse
import effective.office.marvelproject.data.network.paging.MarvelPagingSource
import effective.office.marvelproject.presentation.model.CharacterUI
import kotlinx.coroutines.flow.map

class MarvelRepository(
    private val database: MarvelAppDatabase
) {
    private val characterDao = database.characterDao()

    private val PAGE_SIZE = 20

    @ExperimentalPagingApi
    fun getCharacterPage() =
        Pager(
            config = PagingConfig(
                PAGE_SIZE,
                enablePlaceholders = true
            )
        ) {
            MarvelPagingSource(database)
        }.flow.map { data ->
            data.map {
                it.toUI()
            }
        }


    suspend fun getCharacter(id: Int): Either<ErrorResponse, CharacterUI> {
        val character = characterDao.getCharacter(id)
        return if (character == null) {
            Either.Fail(ErrorResponse(R.string.unknown_error))
        } else {
            Either.Success(character.toUI())
        }
    }
}
