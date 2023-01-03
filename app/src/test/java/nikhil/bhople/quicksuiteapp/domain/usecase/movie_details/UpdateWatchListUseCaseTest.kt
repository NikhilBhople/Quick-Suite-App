package nikhil.bhople.quicksuiteapp.domain.usecase.movie_details

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import nikhil.bhople.quicksuiteapp.data.repository.FakeMovieRepositoryImpl
import org.junit.Before
import org.junit.Test

class UpdateWatchListUseCaseTest {

    private lateinit var useCase: UpdateWatchListUseCase
    private val repository = FakeMovieRepositoryImpl()

    @Before
    fun setUp() {
        useCase = UpdateWatchListUseCase(repository)
    }

    @Test
    fun `Check watch list for movie get updated correctly`() = runBlocking {
        repository.loadJsonValuesToDatabase()
        val watchListStatusBefore = repository.getMovieDetailsStream(2).first().data!!.isAddedToWatchList
        useCase(2)
        val watchListStatusAfter = repository.getMovieDetailsStream(2).first().data!!.isAddedToWatchList
        assert(watchListStatusBefore != watchListStatusAfter)
    }
}