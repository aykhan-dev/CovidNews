package ev.aykhan.covid.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "statistics")
data class Statistics(
    @PrimaryKey val title: String,
    val count: String
)