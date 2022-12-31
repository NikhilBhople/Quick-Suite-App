package nikhil.bhople.quicksuiteapp.data.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import nikhil.bhople.quicksuiteapp.domain.model.Movie

@Entity(tableName = "movie_details_table")
data class MovieDetails(
    @PrimaryKey val id: Int,
    val description: String,
    val duration: String,
    val genre: String,
    val isAddedToWatchList: Boolean,
    val poster: String,
    val rating: Double,
    val releasedDate: String,
    val title: String,
    val trailerLink: String
)

fun MovieDetails.toMovie(): Movie {
    return Movie(
        id = this.id,
        duration = this.duration,
        genre = this.genre,
        poster = this.poster,
        releasedDate = this.releasedDate,
        title = this.title,
        isAddedToWatchList = this.isAddedToWatchList
    )
}