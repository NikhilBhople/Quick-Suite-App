package nikhil.bhople.quicksuiteapp.domain.usecase.movie_list

import nikhil.bhople.quicksuiteapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetJsonDataUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke() {
        repository.loadJsonValuesToDatabase()
    }
}