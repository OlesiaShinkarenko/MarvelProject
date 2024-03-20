package effective.office.marvelproject.network

import effective.office.marvelproject.network.either.Either
import effective.office.marvelproject.network.model.ErrorResponse
import effective.office.marvelproject.network.model.MoshiHeroesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApiService {
    @GET("characters")
    suspend fun getHeroes(): Either<ErrorResponse, MoshiHeroesResponse>

    @GET("characters/{characterId}")
    suspend fun getHero(
        @Path("characterId") id: Int
    ): Either<ErrorResponse, MoshiHeroesResponse>
}

object MarvelApi {
    val retrofitService: MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}