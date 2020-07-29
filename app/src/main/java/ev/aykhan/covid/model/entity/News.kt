package ev.aykhan.covid.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey val id: String,
    val title: String,
    val body: String,
    val datetime: String
)