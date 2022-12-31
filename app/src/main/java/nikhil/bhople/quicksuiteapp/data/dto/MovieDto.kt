package nikhil.bhople.quicksuiteapp.data.dto

import com.google.gson.annotations.SerializedName
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails

data class MovieDto(
    val description: String,
    val duration: Int,
    val genre: List<String>,
    val id: Int,
    var isAddedToWatchList: Boolean,
    val poster: String,
    val rating: Double,
    @SerializedName("released_date")
    val releasedDate: String,
    val title: String,
    @SerializedName("trailer_link")
    val trailerLink: String
)

fun convertDurationToHourMinuteFormat(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    return "${hours}h ${remainingMinutes}min"
}

fun MovieDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = this.id,
        duration = convertDurationToHourMinuteFormat(this.duration),
        genre = this.genre.joinToString(separator = ", "),
        poster = this.poster,
        releasedDate = this.releasedDate,
        title = this.title,
        isAddedToWatchList = this.isAddedToWatchList,
        trailerLink = this.trailerLink,
        rating = this.rating,
        description = this.description
    )
}