package effective.office.marvelproject.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Thumbnail(
    val path: String,
    val extension: String
)