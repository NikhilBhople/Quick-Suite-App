package nikhil.bhople.quicksuiteapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nikhil.bhople.quicksuiteapp.data.common.Constants
import nikhil.bhople.quicksuiteapp.presentation.movie_details.components.MovieDetailsScreen
import nikhil.bhople.quicksuiteapp.presentation.movie_list.components.MainScreen
import nikhil.bhople.quicksuiteapp.presentation.theme.QuickSuiteAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickSuiteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    LoadScreen()
                }
            }
        }
    }

    @Composable
    private fun LoadScreen() {
        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = Screen.MovieListScreen.route
        ) {
            composable(route = Screen.MovieListScreen.route) {
                MainScreen(onMovieItemClick = { movieId ->
                    navController.navigate(route = Screen.MovieDetailsScreen.route + "/${movieId}")
                })
            }
            composable(route = Screen.MovieDetailsScreen.route + "/{${Constants.PARAMS_MOVIE_ID}}") {
                MovieDetailsScreen(onBackBtnClick = { navController.popBackStack() })
            }
        }
    }
}
