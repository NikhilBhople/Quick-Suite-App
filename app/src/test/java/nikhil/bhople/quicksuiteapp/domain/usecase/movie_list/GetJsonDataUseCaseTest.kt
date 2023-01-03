package nikhil.bhople.quicksuiteapp.domain.usecase.movie_list

import kotlinx.coroutines.runBlocking
import nikhil.bhople.quicksuiteapp.data.repository.FakeMovieRepositoryImpl
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetJsonDataUseCaseTest {

    private lateinit var useCase: GetJsonDataUseCase
    private val repositoryImpl = FakeMovieRepositoryImpl()

    @Before
    fun setUp() {
        useCase = GetJsonDataUseCase(repositoryImpl)
    }

    @Test
    fun `GetJsonDataUseCaseTest return the data correctly`() = runBlocking {
        useCase()
        repositoryImpl.getMovieList().collect {
            assertTrue(it.data!!.isNotEmpty())
        }
    }
}