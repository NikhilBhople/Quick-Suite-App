package nikhil.bhople.quicksuiteapp.presentation.movie_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nikhil.bhople.quicksuiteapp.R
import nikhil.bhople.quicksuiteapp.domain.usecase.movie_list.Menu
import nikhil.bhople.quicksuiteapp.presentation.movie_list.MovieListViewModel

@Composable
fun SortMenu(viewModel: MovieListViewModel = hiltViewModel()) {
    var mDisplayMenu by remember { mutableStateOf(false) }
    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(R.string.txt_sort),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
                    .clickable { mDisplayMenu = mDisplayMenu.not() })
        }
    }, elevation = 0.dp, backgroundColor = Color.White, actions = {
        DropdownMenu(expanded = mDisplayMenu, onDismissRequest = { mDisplayMenu = false }) {
            DropdownMenuItem(onClick = {
                mDisplayMenu = mDisplayMenu.not()
                viewModel.sortListBy(Menu.TITLE)
            }) {
                Text(text = stringResource(R.string.txt_title))
            }
            DropdownMenuItem(onClick = {
                mDisplayMenu = mDisplayMenu.not()
                viewModel.sortListBy(Menu.RELEASED_DATE)
            }) {
                Text(text = stringResource(R.string.txt_release_date))
            }
        }
    })
}