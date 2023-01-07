package nikhil.bhople.quicksuiteapp.presentation.movie_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nikhil.bhople.quicksuiteapp.data.common.Constants.EMPTY_STRING
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_list.GetMovieListUseCase
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_list.Menu
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_list.SortMovieListUseCase
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val sortMovieListUseCase: SortMovieListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(MovieListState())
    val state = _state

    init {
        getMovieList()
    }

    fun getMovieList() {
        viewModelScope.launch {
            getMovieListUseCase().collect { result ->
                when (result) {
                    is Resource.Failure -> _state.value =
                        MovieListState(errorMessage = result.message ?: EMPTY_STRING)
                    is Resource.Loading -> _state.value = MovieListState(isLoading = true)
                    is Resource.Success -> _state.value =
                        MovieListState(data = result.data ?: emptyList())
                }
            }
        }
    }

    fun sortListBy(menu: Menu) {
        _state.value = _state.value.copy(isLoading = true)
        sortMovieListUseCase(menu, state.value.data).onEach {
            _state.value = MovieListState(data = it.data ?: emptyList(), isLoading = false)
        }.launchIn(viewModelScope)
    }
}