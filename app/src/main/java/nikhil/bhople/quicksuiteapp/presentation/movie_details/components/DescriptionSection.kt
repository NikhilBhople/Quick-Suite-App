package nikhil.bhople.quicksuiteapp.presentation.movie_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails

@Composable
internal fun DescriptionSection(movieDetails: MovieDetails) {
    Column {
        Text(
            text = "Short Description",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = movieDetails.description,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}