package effective.office.marvelproject.network.model

import com.squareup.moshi.JsonClass
import java.security.cert.Extension

@JsonClass(generateAdapter = true)
data class CharacterDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
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
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: CharacterDataContainer,
    val etag: String
)
