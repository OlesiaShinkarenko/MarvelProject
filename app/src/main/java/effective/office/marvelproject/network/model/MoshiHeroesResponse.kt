package effective.office.marvelproject.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroesDataContainer(
    @Json(name = "results")
    val results: List<Character>
)
@JsonClass(generateAdapter = true)
data class Character(
    val id: Int,
    val name: String,
    val description:String,
    val thumbnail:Thumbnail
)
@JsonClass(generateAdapter = true)
data class Thumbnail (
    val path:String,
    val extension: String
)

@JsonClass(generateAdapter = true)
data class MoshiHeroesResponse(
    @Json(name = "data")
    val data: HeroesDataContainer,
)
