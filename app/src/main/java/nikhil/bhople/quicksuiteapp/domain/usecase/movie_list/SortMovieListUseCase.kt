package nikhil.bhople.quicksuiteapp.domain.usecase.movie_list

import kotlinx.coroutines.flow.flow
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.domain.model.Movie
import javax.inject.Inject

class SortMovieListUseCase @Inject constructor(
) {
    operator fun invoke(sortBy: Menu, data: List<Movie>) = flow<Resource<List<Movie>>> {
        emit((Resource.Success(data.sortedBy {
            when (sortBy) {
                Menu.TITLE -> it.title
                Menu.RELEASED_DATE -> it.releasedDate
            }
        })))
    }
}

enum class Menu {
    TITLE, RELEASED_DATE
}