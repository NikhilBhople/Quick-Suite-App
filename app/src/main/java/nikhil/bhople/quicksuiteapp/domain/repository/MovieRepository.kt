package nikhil.bhople.quicksuiteapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.domain.model.Movie
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails

interface MovieRepository {
    fun getMovieList(): Flow<Resource<List<Movie>>>
    fun getMovieDetailsStream(movieId: Int): Flow<Resource<MovieDetails>>
    suspend fun updateMovieWatchList(movieId: Int)
    suspend fun loadJsonValuesToDatabase()
}