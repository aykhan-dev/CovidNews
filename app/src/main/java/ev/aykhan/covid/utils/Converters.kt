package ev.aykhan.covid.utils

import ev.aykhan.covid.model.entity.Country
import ev.aykhan.covid.model.entity.News
import ev.aykhan.covid.model.entity.Statistics
import ev.aykhan.covid.model.parcelable.NewsParcelable
import ev.aykhan.covid.model.pojo.CountryPOJO
import ev.aykhan.covid.model.pojo.NewsPOJO
import ev.aykhan.covid.model.pojo.StatisticsPOJO

fun StatisticsPOJO.toStatisticsList(): List<Statistics> {
    return listOf(
        Statistics("Active Cases", activeCases),
        Statistics("Critical Cases", criticalCases),
        Statistics("New Cases", newCases),
        Statistics("New Deaths", newDeaths),
        Statistics("Per Mln", perMln),
        Statistics("Total Cases", totalCases),
        Statistics("Total Deaths", totalDeaths),
        Statistics("Total Recovered", totalRecovered)
    )
}

fun List<NewsPOJO>.toNewsList(): List<News> = map {
    News(
        id = it.id,
        title = it.title,
        body = it.body,
        datetime = it.datetime
    )
}

fun List<CountryPOJO>.toCountryList(): List<Country> = map {
    Country(
        country = it.country,
        totalRecovered = it.totalRecovered,
        totalDeaths = it.totalDeaths,
        totalCases = it.totalCases,
        newDeaths = it.newDeaths,
        newCases = it.newCases
    )
}

fun News.toParcelable(): NewsParcelable {
    return NewsParcelable(id, title, body, datetime)
}