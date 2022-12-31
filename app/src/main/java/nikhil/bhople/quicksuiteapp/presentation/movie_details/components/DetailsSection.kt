package nikhil.bhople.quicksuiteapp.presentation.movie_details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nikhil.bhople.quicksuiteapp.R
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails

@Composable
internal fun DetailsSection(movieDetails: MovieDetails) {
    Column {
        Text(
            text = stringResource(R.string.txt_details),
            style = MaterialTheme.typography.h6
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.txt_genre),
                style = MaterialTheme.typography.body2,
                color = Color.Black,
                textAlign = TextAlign.End,
                modifier = Modifier.width(108.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = movieDetails.genre,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.txt_released_date),
                style = MaterialTheme.typography.body2,
                color = Color.Black,
                textAlign = TextAlign.End,
                modifier = Modifier.width(108.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = movieDetails.releasedDate,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
        }
    }
}