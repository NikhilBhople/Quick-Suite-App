package nikhil.bhople.quicksuiteapp.presentation.movie_list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails
import nikhil.bhople.quicksuiteapp.domain.model.Movie

@Composable
fun MovieList(
    onMovieItemClick: (Int) -> Unit, movieList: List<Movie>
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movieList) { movie: Movie ->
            MovieListItem(movie = movie, onItemClick = { onMovieItemClick(it) })
            Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }
    }
}