package nikhil.bhople.quicksuiteapp.presentation.movie_details.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nikhil.bhople.quicksuiteapp.presentation.movie_details.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    onBackBtnClick: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {

        state.data?.let { movieDetails ->
            Column {
                ToolBar(onBackClick = onBackBtnClick)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    item {
                        HeaderSection(
                            movieDetails,
                            onTrailerBtnClick = { viewModel.handleTrailerClick(it) },
                            onUpdateWatchListBtnClick = { viewModel.updateAddToWatchList(it) }
                        )
                        Divider(modifier = Modifier.padding(top = 16.dp, bottom = 24.dp))
                        DescriptionSection(movieDetails)
                        Divider(modifier = Modifier.padding(top = 24.dp, bottom = 24.dp))
                        DetailsSection(movieDetails)
                    }
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (state.errorMessage.isNotEmpty()) {
            Toast.makeText(LocalContext.current, state.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}

