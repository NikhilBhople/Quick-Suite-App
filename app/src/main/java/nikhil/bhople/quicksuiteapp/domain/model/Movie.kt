package nikhil.bhople.quicksuiteapp.domain.model

data class Movie(
    val duration: String,
    val genre: String,
    val id: Int,
    val isAddedToWatchList: Boolean,
    val poster: String,
    val releasedDate: String,
    val title: String,
)
