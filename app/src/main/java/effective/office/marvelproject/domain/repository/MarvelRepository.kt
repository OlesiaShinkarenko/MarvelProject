package effective.office.marvelproject.domain.repository

import androidx.paging.PagingData
import effective.office.marvelproject.data.network.Either
import effective.office.marvelproject.data.remote.model.ErrorResponse
import effective.office.marvelproject.presentation.models.CharacterUI
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    fun getCharacterPage(): Flow<PagingData<CharacterUI>>
    suspend fun getCharacter(id: Int): Flow<Either<ErrorResponse, CharacterUI>>
}