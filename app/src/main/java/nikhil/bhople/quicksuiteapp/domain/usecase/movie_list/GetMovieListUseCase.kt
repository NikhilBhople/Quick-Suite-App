package nikhil.bhople.quicksuiteapp.domain.usecase.movie_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import nikhil.bhople.quicksuiteapp.data.common.Constants
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.domain.model.Movie
import nikhil.bhople.quicksuiteapp.domain.repository.MovieRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            emitAll(repository.getMovieList())
        } catch (e: HttpException) {
            emit(Resource.Failure(Constants.HTTP_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Failure(Constants.INTERNET_CONNECTION_MSG))
        }
    }
}
