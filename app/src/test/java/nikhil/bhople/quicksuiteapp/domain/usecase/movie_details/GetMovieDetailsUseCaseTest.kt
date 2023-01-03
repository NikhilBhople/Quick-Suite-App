package nikhil.bhople.quicksuiteapp.domain.usecase.movie_details

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import nikhil.bhople.quicksuiteapp.data.repository.FakeMovieRepositoryImpl

import org.junit.Before
import org.junit.Test

class GetMovieDetailsUseCaseTest {
    private lateinit var useCase: GetMovieDetailsUseCase
    private val repository = FakeMovieRepositoryImpl()

    @Before
    fun setUp() {
        useCase = GetMovieDetailsUseCase(repository)
    }

    @Test
    fun `Use case should return Failure when the movie details is not present`() = runBlocking {
        val result = useCase(2).first()
        assert(result.message != null)
    }

    @Test
    fun `Use case should return Success when the movie details is present`() = runBlocking {
        repository.loadJsonValuesToDatabase()
        val result = useCase(2).first()
        assert(result.data != null)
    }
}