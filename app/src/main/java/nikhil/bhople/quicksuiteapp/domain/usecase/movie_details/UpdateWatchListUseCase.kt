package nikhil.bhople.quicksuiteapp.domain.usecase.movie_details

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.domain.repository.MovieRepository
import javax.inject.Inject

class UpdateWatchListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int) {
        withContext(Dispatchers.IO) {
            Resource.Success(repository.updateMovieWatchList(movieId))
        }
    }
}