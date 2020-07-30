package ev.aykhan.covid.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localStatistics")
data class LocalStatistics(
    @PrimaryKey val title: String,
    val count: String
)