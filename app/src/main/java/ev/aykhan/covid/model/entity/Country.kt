package ev.aykhan.covid.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey val country: String,
    val newCases: String,
    val newDeaths: String,
    val totalCases: String,
    val totalDeaths: String,
    val totalRecovered: String
)