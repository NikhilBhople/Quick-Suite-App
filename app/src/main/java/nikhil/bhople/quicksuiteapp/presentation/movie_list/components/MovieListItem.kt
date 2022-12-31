package nikhil.bhople.quicksuiteapp.presentation.movie_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import nikhil.bhople.quicksuiteapp.R
import nikhil.bhople.quicksuiteapp.data.common.AssetsUtils
import nikhil.bhople.quicksuiteapp.domain.model.Movie
import nikhil.bhople.quicksuiteapp.presentation.theme.Shapes

@Composable
internal fun MovieListItem(
    movie: Movie,
    onItemClick: (Int) -> Unit
) {

    Row(modifier = Modifier
        .height(184.dp)
        .fillMaxWidth()
        .clickable { onItemClick(movie.id) }
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        AssetsUtils.getDrawableByName(movie.poster)?.let {
            Image(
                modifier = Modifier
                    .height(152.dp)
                    .width(100.dp)
                    .clip(Shapes.small),
                painter = painterResource(id = it),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${movie.title} (${movie.releasedDate.split(" ").last()})",
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${movie.duration} - ${movie.genre}",
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
            if (movie.isAddedToWatchList) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.txt_on_my_watch_list),
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }

}