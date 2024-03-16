package effective.office.marvelproject.network

import effective.office.marvelproject.network.model.MoshiHeroesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("characters")
    suspend fun getHeroes(
        @Query("ts") ts: String = Constant.ts,
        @Query("hash") hash: String = Constant.hash(),
    ): Response<MoshiHeroesResponse>

    @GET("characters/{characterId}")
    suspend fun getHero(
        @Path("characterId") id: Int,
        @Query("ts") ts: String = Constant.ts,
        @Query("hash") hash: String = Constant.hash()
    ): Response<MoshiHeroesResponse>

}

object MarvelApi {
    val retrofitService: MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}