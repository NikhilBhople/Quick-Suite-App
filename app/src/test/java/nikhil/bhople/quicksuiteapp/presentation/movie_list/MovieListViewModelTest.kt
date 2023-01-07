package nikhil.bhople.quicksuiteapp.presentation.movie_list

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import nikhil.bhople.quicksuiteapp.MainCoroutineRule
import nikhil.bhople.quicksuiteapp.data.common.Constants
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.data.repository.FakeMovieRepositoryImpl
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_list.GetMovieListUseCase
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_list.Menu
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_list.SortMovieListUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {

    private val fakeRepository = FakeMovieRepositoryImpl()
    private val sortMovieListUseCase = SortMovieListUseCase()
    private lateinit var viewModel: MovieListViewModel
    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @get:Rule // it will set and reset the UnconfinedTestDispatcher
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        getMovieListUseCase = GetMovieListUseCase(fakeRepository)

        viewModel = MovieListViewModel(
            getMovieListUseCase = getMovieListUseCase, sortMovieListUseCase = sortMovieListUseCase
        )
    }

    @Test
    fun `Loading state should be observed in the beginning`() = runBlocking {
        val first = getMovieListUseCase().first()
        val isLoadingState = first is Resource.Loading
        assert(isLoadingState)
    }

    @Test
    fun `Should Observer the data when movie list fetching is successful from repository`() =
        runBlocking {
            val resourceList = getMovieListUseCase().take(2).toList()
            assert(resourceList[0] is Resource.Loading)
            assert(resourceList[1] is Resource.Success)
            assert(resourceList[1].data!!.isNotEmpty())
        }

    @Test
    fun `Should Observer HTTP_ERROR_MSG when HttpException is occurred while fetching data from repository`() =
        runBlocking {
            fakeRepository.testHttpException()
            val resourceList = getMovieListUseCase().take(2).toList()
            assert(resourceList[0] is Resource.Loading)
            assert(resourceList[1] is Resource.Failure)
            assert(resourceList[1].message == Constants.HTTP_ERROR_MSG)
        }

    @Test
    fun `Should Observer INTERNET_CONNECTION_MSG when IOException is occurred while fetching data from repository`() =
        runBlocking {
            fakeRepository.testIOException()
            val resourceList = getMovieListUseCase().take(2).toList()
            assert(resourceList[0] is Resource.Loading)
            assert(resourceList[1] is Resource.Failure)
            assert(resourceList[1].message == Constants.INTERNET_CONNECTION_MSG)
        }

    @Test
    fun `sortListBy TITLE should return sorted movie list by title`() = runBlocking {
        viewModel.sortListBy(Menu.TITLE)
        val sortedList = viewModel.state.value.data
        val isMovieListSortedByTitle =
            sortedList.zipWithNext().all { it.first.title <= it.second.title }
        assert(isMovieListSortedByTitle)
    }

    @Test
    fun `sortListBy RELEASED_DATE should return sorted movie list by released date`() =
        runBlocking {
            viewModel.sortListBy(Menu.RELEASED_DATE)
            val sortedList = viewModel.state.value.data
            val isMovieListSortedByReleasedDate =
                sortedList.zipWithNext().all { it.first.releasedDate <= it.second.releasedDate }
            assert(isMovieListSortedByReleasedDate)
        }
}