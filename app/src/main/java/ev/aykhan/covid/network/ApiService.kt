package ev.aykhan.covid.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ev.aykhan.covid.BuildConfig
import ev.aykhan.covid.model.pojo.CountryPOJO
import ev.aykhan.covid.model.pojo.NewsPOJO
import ev.aykhan.covid.model.pojo.StatisticsPOJO
import ev.aykhan.covid.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CountriesService {

    @GET(".")
    suspend fun getCountries(): Response<List<CountryPOJO>>

}

interface NewsService {

    @GET("news")
    suspend fun getNews(): Response<List<NewsPOJO>>

}

interface StatisticsService {

    @GET("stats")
    suspend fun getGlobalStatistics(): Response<StatisticsPOJO>

}

object ApiInitHelper {

    private val okHttpClientBuilder = OkHttpClient.Builder()
    private var retrofit: Retrofit? = null

    private fun okHttpClient(): OkHttpClient {
        when {
            BuildConfig.DEBUG -> {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                okHttpClientBuilder.addInterceptor(logging)
            }
        }
        return okHttpClientBuilder.build()
    }

    private fun getClient(): Retrofit {
        when (retrofit) {
            null -> {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(
                        MoshiConverterFactory.create(
                            Moshi.Builder()
                                .add(KotlinJsonAdapterFactory())
                                .build()
                        )
                    )
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(okHttpClient())
                    .baseUrl(BASE_URL)
                    .build()
            }
        }
        return retrofit as Retrofit
    }

    val countriesService by lazy { getClient().create(CountriesService::class.java) }
    val newsService by lazy { getClient().create(NewsService::class.java) }
    val statisticsService by lazy { getClient().create(StatisticsService::class.java) }

}