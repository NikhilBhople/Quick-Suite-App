package nikhil.bhople.quicksuiteapp.domain.usecase.movie_details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nikhil.bhople.quicksuiteapp.data.common.Constants
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails
import nikhil.bhople.quicksuiteapp.domain.repository.MovieRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetails>> {
        return repository.getMovieDetailsStream(movieId)
    }
}