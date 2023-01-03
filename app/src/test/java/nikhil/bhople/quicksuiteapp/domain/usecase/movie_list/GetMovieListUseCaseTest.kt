package nikhil.bhople.quicksuiteapp.domain.usecase.movie_list

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import nikhil.bhople.quicksuiteapp.data.repository.FakeMovieRepositoryImpl
import org.junit.Before
import org.junit.Test

class GetMovieListUseCaseTest {
    private lateinit var useCase: GetMovieListUseCase
    private val repository = FakeMovieRepositoryImpl()

    @Before
    fun setUp() {
        useCase = GetMovieListUseCase(repository)
    }

    @Test
    fun `GetMovieListUseCase should return empty list when there is no movie present`() =
        runBlocking {
            val result = useCase().first()
            assert(result.data!!.isEmpty())
        }

    @Test
    fun `GetMovieListUseCase should return the movie list when its fetch from remote`() =
        runBlocking {
            repository.loadJsonValuesToDatabase()
            val result = useCase().first()
            assert(result.data!!.isNotEmpty())
        }
}