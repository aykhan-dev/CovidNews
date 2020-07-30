package ev.aykhan.covid.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "globalStatistics")
data class GlobalStatistics(
    @PrimaryKey val title: String,
    val count: String
)