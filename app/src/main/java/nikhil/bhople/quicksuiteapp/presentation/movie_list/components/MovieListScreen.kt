package nikhil.bhople.quicksuiteapp.presentation.movie_list.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import nikhil.bhople.quicksuiteapp.R
import nikhil.bhople.quicksuiteapp.domain.model.Movie
import nikhil.bhople.quicksuiteapp.presentation.movie_list.MovieListViewModel

@Composable
fun MainScreen(
    onMovieItemClick: (Int) -> Unit,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val uiState = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {

        if (uiState.data.isNotEmpty()) {
            MovieListScreen(
                onMovieItemClick = { onMovieItemClick(it) },
                uiState.data
            )
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    if (uiState.errorMessage.isNotEmpty()) {
        Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun MovieListScreen(
    onMovieItemClick: (Int) -> Unit,
    movieList: List<Movie>
) {
    val toolbarScaffoldState = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(modifier = Modifier,
        state = toolbarScaffoldState,
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            val textSize = (18 + (30 - 12) * toolbarScaffoldState.toolbarState.progress).sp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .pin()
                    .background(color = Color.White)
            )

            SortMenu()

            Text(
                text = stringResource(R.string.txt_movies),
                style = MaterialTheme.typography.h4,
                fontSize = textSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .road(
                        whenCollapsed = Alignment.TopStart, whenExpanded = Alignment.BottomEnd
                    )
            )
        }) {
        MovieList(
            onMovieItemClick = { onMovieItemClick(it) }, movieList = movieList
        )
    }
}
