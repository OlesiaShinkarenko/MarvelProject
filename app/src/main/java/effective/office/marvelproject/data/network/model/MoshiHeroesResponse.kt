package effective.office.marvelproject.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoshiHeroesResponse(
    @Json(name = "data")
    val data: HeroesDataContainer,
)
