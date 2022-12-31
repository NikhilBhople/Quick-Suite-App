package nikhil.bhople.quicksuiteapp.presentation.movie_details.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nikhil.bhople.quicksuiteapp.R

@Composable
fun ToolBar(onBackClick: () -> Unit) {
    TopAppBar(elevation = 4.dp, title = {
        Text(stringResource(R.string.movie), style = MaterialTheme.typography.h5)
    }, backgroundColor = Color.White, navigationIcon = {
        IconButton(onClick = onBackClick) {
            Icon(Icons.Filled.ArrowBack, null)
        }
    })
}