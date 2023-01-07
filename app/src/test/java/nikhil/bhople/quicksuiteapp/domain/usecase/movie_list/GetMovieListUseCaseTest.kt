package nikhil.bhople.quicksuiteapp.domain.usecase.movie_list

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import nikhil.bhople.quicksuiteapp.data.common.Constants
import nikhil.bhople.quicksuiteapp.data.common.Resource
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
    fun `GetMovieListUseCase should emit Loading state when called`() =
        runBlocking {
            val result = useCase().first()
            assert(result is Resource.Loading)
        }

    @Test
    fun `GetMovieListUseCase should emit the movie list when movie data loaded successfully from repository`() =
        runBlocking {
            val resourceList = useCase().take(2).toList()
            assert(resourceList[0] is Resource.Loading)
            assert(resourceList[1] is Resource.Success)
        }

    @Test
    fun `GetMovieListUseCase should emit HTTP_ERROR_MSG when HttpException observed from repository`() =
        runBlocking {
            repository.testHttpException()
            val resourceList = useCase().take(2).toList()
            assert(resourceList[0] is Resource.Loading)
            assert(resourceList[1] is Resource.Failure)
            assert(resourceList[1].message == Constants.HTTP_ERROR_MSG)
        }

    @Test
    fun `GetMovieListUseCase should emit INTERNET_CONNECTION_MSG when IOException observed from repository`() =
        runBlocking {
            repository.testIOException()
            val resourceList = useCase().take(2).toList()
            assert(resourceList[0] is Resource.Loading)
            assert(resourceList[1] is Resource.Failure)
            assert(resourceList[1].message == Constants.INTERNET_CONNECTION_MSG)
        }
}