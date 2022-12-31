package nikhil.bhople.quicksuiteapp.domain.usecase.movie_list

import kotlinx.coroutines.flow.Flow
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.domain.model.Movie
import nikhil.bhople.quicksuiteapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> {
        return repository.getMovieList()
    }
}
