package nikhil.bhople.quicksuiteapp.presentation.movie_details

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import nikhil.bhople.quicksuiteapp.MainCoroutineRule
import nikhil.bhople.quicksuiteapp.data.common.Constants
import nikhil.bhople.quicksuiteapp.data.repository.FakeMovieRepositoryImpl
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_details.GetMovieDetailsUseCase
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_details.UpdateWatchListUseCase
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailsViewModelTest {
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private lateinit var updateWatchListUseCase: UpdateWatchListUseCase
    private val repository = FakeMovieRepositoryImpl()
    private val savedStateHandle = SavedStateHandle(mapOf(Constants.PARAMS_MOVIE_ID to "1"))

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        getMovieDetailsUseCase = GetMovieDetailsUseCase(repository)
        updateWatchListUseCase = UpdateWatchListUseCase(repository)
        viewModel = MovieDetailsViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            savedStateHandle = savedStateHandle,
            updateWatchListUseCase = updateWatchListUseCase
        )
    }

    @Test
    fun `ViewModel should receive the movie id form SaveStateHandle`() {
        assertTrue(viewModel.requestedMovieIdDetails != null)
    }

    @Test
    fun `getMovieDetails should return Error when the movie details are not present`() =
        runBlocking {
            assertTrue(viewModel.state.value.errorMessage.isNotEmpty())
        }

    @Test
    fun `getMovieDetails should return movie details when the movie details are present`() =
        runBlocking {
            repository.loadJsonValuesToDatabase()
            viewModel.getMovieDetails()
            assertTrue(viewModel.state.value.data != null)
        }

    @Test
    fun `updateAddToWatchList should update isAddedToWatchList value for movie`() = runBlocking {
        repository.loadJsonValuesToDatabase()
        viewModel.getMovieDetails()
        val addToWatchListValueBefore = viewModel.state.value.data!!.isAddedToWatchList
        viewModel.updateAddToWatchList(1)
        val addToWatchListValueAfter =
            repository.getMovieDetailsStream(1).first().data?.isAddedToWatchList
        assertTrue(addToWatchListValueBefore != addToWatchListValueAfter)
    }
}