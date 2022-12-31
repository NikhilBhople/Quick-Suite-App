package nikhil.bhople.quicksuiteapp.presentation.movie_list

import nikhil.bhople.quicksuiteapp.data.common.Constants
import nikhil.bhople.quicksuiteapp.domain.model.Movie

data class MovieListState(
    val data: List<Movie> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String = Constants.EMPTY_STRING
)
