package ev.aykhan.covid.model.pojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class GlobalStatisticsPOJO(
    @Json(name = "active_cases") val activeCases: String,
    @Json(name = "critical_cases") val criticalCases: String,
    @Json(name = "new_cases") val newCases: String,
    @Json(name = "new_deaths") val newDeaths: String,
    @Json(name = "per_mln") val perMln: String,
    @Json(name = "total_cases") val totalCases: String,
    @Json(name = "total_deaths") val totalDeaths: String,
    @Json(name = "total_recovered") val totalRecovered: String
)