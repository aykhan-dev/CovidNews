package ev.aykhan.covid.model.pojo

import com.squareup.moshi.Json

data class NewsPOJO(
    @Json(name = "body") val body: String,
    @Json(name = "datetime") val datetime: String,
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String
)