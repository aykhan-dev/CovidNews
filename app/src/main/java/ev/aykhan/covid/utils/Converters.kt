package ev.aykhan.covid.utils

import ev.aykhan.covid.model.entity.Country
import ev.aykhan.covid.model.entity.GlobalStatistics
import ev.aykhan.covid.model.entity.News
import ev.aykhan.covid.model.parcelable.NewsParcelable
import ev.aykhan.covid.model.pojo.CountryPOJO
import ev.aykhan.covid.model.pojo.GlobalStatisticsPOJO
import ev.aykhan.covid.model.pojo.NewsPOJO

fun GlobalStatisticsPOJO.toStatisticsList(titles: Array<String>): List<GlobalStatistics> {
    return listOf(
        GlobalStatistics(titles[0], activeCases),
        GlobalStatistics(titles[1], criticalCases),
        GlobalStatistics(titles[2], newCases),
        GlobalStatistics(titles[3], newDeaths),
        GlobalStatistics(titles[4], perMln),
        GlobalStatistics(titles[5], totalCases),
        GlobalStatistics(titles[6], totalDeaths),
        GlobalStatistics(titles[7], totalRecovered)
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