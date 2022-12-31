package nikhil.bhople.quicksuiteapp.presentation.movie_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nikhil.bhople.quicksuiteapp.data.common.Constants
import nikhil.bhople.quicksuiteapp.data.common.Constants.EMPTY_STRING
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_details.GetMovieDetailsUseCase
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_details.UpdateWatchListUseCase
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle,
    private val updateWatchListUseCase: UpdateWatchListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(MovieDetailState())
    val state = _state

    init {
        savedStateHandle.get<String>(Constants.PARAMS_MOVIE_ID)?.let { movieId ->
            _state.value = _state.value.copy(movieId = movieId.toInt())
            getMovieDetails()
        }
    }

    private fun getMovieDetails() {
        _state.value.movieId?.let { movieId ->
            getMovieDetailsUseCase(movieId).onEach { response ->
                when (response) {
                    is Resource.Failure -> _state.value =
                        MovieDetailState(errorMessage = response.message ?: EMPTY_STRING)
                    is Resource.Loading -> _state.value = MovieDetailState(isLoading = true)
                    is Resource.Success -> _state.value = MovieDetailState(data = response.data)
                }
            }.launchIn(viewModelScope)
        }
    }

    fun handleTrailerClick(trailerLink: String) {
      /* You can handle the way you want
         Either you can play trailer inside app
         or send open it in YouTube app   */
    }

    fun updateAddToWatchList(movieId: Int) {
        viewModelScope.launch {
            updateWatchListUseCase(movieId)
        }
    }
}