package nikhil.bhople.quicksuiteapp.domain.usecase.movie_details

import nikhil.bhople.quicksuiteapp.domain.repository.MovieRepository
import javax.inject.Inject

class UpdateWatchListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int) {
        repository.updateMovieWatchList(movieId)
    }
}