package nikhil.bhople.quicksuiteapp.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nikhil.bhople.quicksuiteapp.data.common.Constants.EMPTY_STRING
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.data.data_source.local.MovieDao
import nikhil.bhople.quicksuiteapp.domain.model.Movie
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.toMovie
import nikhil.bhople.quicksuiteapp.data.data_source.remote.AssetManager
import nikhil.bhople.quicksuiteapp.data.data_source.remote.RetrofitMovieDataSource
import nikhil.bhople.quicksuiteapp.data.dto.toMovieDetails
import nikhil.bhople.quicksuiteapp.domain.repository.MovieRepository
import javax.inject.Inject

// For real world application we will be using RetrofitMovieDataSource
class MovieRepositoryImpl @Inject constructor(
    private val retrofitApiRemoteDataSource: RetrofitMovieDataSource,
    private val localDataSource: MovieDao,
    private val remoteDataSource: AssetManager,
    private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {

    override fun getMovieList(): Flow<Resource<List<Movie>>> {
        return try {
            localDataSource.getMovieList().map { Resource.Success(it.map { it.toMovie() }) }
        } catch (e: Exception) {
            flowOf(Resource.Failure(e.localizedMessage ?: EMPTY_STRING))
        }
    }

    override fun getMovieDetailsStream(movieId: Int): Flow<Resource<MovieDetails>> {
        return try {
            localDataSource.getMovieDetailsStream(movieId).map {
                Resource.Success(it)
            }
        } catch (e: Exception) {
            flowOf(Resource.Failure(e.localizedMessage ?: EMPTY_STRING))
        }
    }

    override suspend fun updateMovieWatchList(movieId: Int) {
        coroutineScope {
            launch {
                val old = localDataSource.getMovieDetails(movieId)
                val new = old.copy(isAddedToWatchList = old.isAddedToWatchList.not())
                localDataSource.updateMovieWatchList(new)
            }
        }
    }

    // We can specify the condition when to fetch remote API depending on app requirement
    override suspend fun loadJsonValuesToDatabase() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch {
                    if (localDataSource.count() == 0) {
                        localDataSource.addMoviesToDatabase(
                            remoteDataSource.getMovieListFromLocalJson()
                                .map { it.toMovieDetails() }
                        )
                    }
                }
            }
        }
    }
}