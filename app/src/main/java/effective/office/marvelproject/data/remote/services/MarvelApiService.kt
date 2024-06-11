package effective.office.marvelproject.data.remote.services

import effective.office.marvelproject.data.network.Either
import effective.office.marvelproject.data.remote.model.ErrorResponse
import effective.office.marvelproject.data.remote.model.MoshiHeroesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("characters")
    suspend fun getHeroes(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Either<ErrorResponse, MoshiHeroesResponse>

    @GET("characters/{characterId}")
    suspend fun getHero(
        @Path("characterId") id: Int
    ): Either<ErrorResponse, MoshiHeroesResponse>
}