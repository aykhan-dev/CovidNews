package ev.aykhan.covid.model.pojo

import com.squareup.moshi.Json

data class CountryPOJO(
    @Json(name = "country") val country: String,
    @Json(name = "new_cases") val newCases: String,
    @Json(name = "new_deaths") val newDeaths: String,
    @Json(name = "total_cases") val totalCases: String,
    @Json(name = "total_deaths") val totalDeaths: String,
    @Json(name = "total_recovered") val totalRecovered: String
)