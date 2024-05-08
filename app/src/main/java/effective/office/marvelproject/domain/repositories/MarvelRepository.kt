package effective.office.marvelproject.domain.repositories

import androidx.paging.PagingData
import effective.office.marvelproject.data.network.either.Either
import effective.office.marvelproject.data.network.model.ErrorResponse
import effective.office.marvelproject.presentation.model.CharacterUI
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    fun getCharacterPage(): Flow<PagingData<CharacterUI>>
    suspend fun getCharacter(id: Int): Either<ErrorResponse, CharacterUI>
}