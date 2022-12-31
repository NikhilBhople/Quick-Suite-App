package nikhil.bhople.quicksuiteapp.presentation.movie_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import nikhil.bhople.quicksuiteapp.R
import nikhil.bhople.quicksuiteapp.data.common.AssetsUtils
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails
import nikhil.bhople.quicksuiteapp.presentation.theme.Shapes

@Composable
internal fun HeaderSection(
    data: MovieDetails,
    onTrailerBtnClick: (String) -> Unit,
    onUpdateWatchListBtnClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        PosterCompose(data.poster, data.title)

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TitleCompose(data.title, data.rating)

            Spacer(modifier = Modifier.height(24.dp))

            WatchListButtonCompose(onUpdateWatchListBtnClick = {
                onUpdateWatchListBtnClick(data.id)
            }, data.isAddedToWatchList)

            Spacer(modifier = Modifier.height(16.dp))

            WatchTrailerButtonCompose(onTrailerBtnClick, data.trailerLink)

        }
    }
}

@Composable
private fun PosterCompose(poster: String, title: String) {
    AssetsUtils.getDrawableByName(poster)?.let {
        Image(
            modifier = Modifier
                .height(176.dp)
                .width(120.dp)
                .padding(end = 16.dp)
                .clip(Shapes.small),
            painter = painterResource(id = it),
            contentDescription = title,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun TitleCompose(
    title: String, rating: Double
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$rating",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5,
            )
            Text(
                text = stringResource(R.string.txt_rating_out_of),
                style = MaterialTheme.typography.body2,
            )
        }

    }
}

@Composable
private fun WatchListButtonCompose(
    onUpdateWatchListBtnClick: () -> Unit, isAddedToWatchList: Boolean
) {
    Box(modifier = Modifier
        .noRippleClickable {
            onUpdateWatchListBtnClick()
        }
        .background(
            color = Color.LightGray, shape = RoundedCornerShape(100.dp)
        )
        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)) {
        Text(
            text = if (isAddedToWatchList) stringResource(R.string.btn_remove_from_watch_list)
            else stringResource(R.string.btn_add_to_watch_list),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun WatchTrailerButtonCompose(
    onTrailerBtnClick: (String) -> Unit, trailerLink: String
) {
    Box(modifier = Modifier
        .border(
            width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(100.dp)
        )
        .noRippleClickable { onTrailerBtnClick(trailerLink) }) {
        Text(
            text = stringResource(R.string.btn_watch_trailer),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp
            )
        )
    }
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}