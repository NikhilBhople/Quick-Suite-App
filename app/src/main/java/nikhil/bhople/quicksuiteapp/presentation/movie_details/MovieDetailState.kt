package nikhil.bhople.quicksuiteapp.presentation.movie_details

import nikhil.bhople.quicksuiteapp.data.common.Constants
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails

data class MovieDetailState(
    val movieId: Int? = null,
    val data: MovieDetails? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = Constants.EMPTY_STRING
)
