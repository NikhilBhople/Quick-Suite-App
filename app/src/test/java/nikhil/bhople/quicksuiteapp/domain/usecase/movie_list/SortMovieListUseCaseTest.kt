package nikhil.bhople.quicksuiteapp.domain.usecase.movie_list

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import nikhil.bhople.quicksuiteapp.data.repository.FakeMovieRepositoryImpl
import nikhil.bhople.quicksuiteapp.domain.model.Movie
import org.junit.Before
import org.junit.Test

class SortMovieListUseCaseTest {

    private lateinit var movieList: List<Movie>
    private val sortMovieListUseCase = SortMovieListUseCase()

    @Before
    fun setUp() {
        runBlocking {
            val repository = FakeMovieRepositoryImpl()
            repository.loadJsonValuesToDatabase()
            movieList = repository.getMovieList().first().data!!
        }
    }

    @Test
    fun `When sorted by title usecase should return sorted list by title`() = runBlocking {
        val result = sortMovieListUseCase(Menu.TITLE, movieList).first().data!!
        val isSortedByTitle = result.zipWithNext().all { it.first.title <= it.second.title}
        assert(isSortedByTitle)
    }

    @Test
    fun `When sorted by released date usecase should return sorted list by release date`() = runBlocking {
        val result = sortMovieListUseCase(Menu.RELEASED_DATE, movieList).first().data!!
        val isSortedByReleasedDate = result.zipWithNext().all { it.first.releasedDate <= it.second.releasedDate}
        assert(isSortedByReleasedDate)
    }

}