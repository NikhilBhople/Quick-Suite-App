package nikhil.bhople.quicksuiteapp.presentation

sealed class Screen(val route: String) {
    object MovieListScreen : Screen("movie_list_screen")
    object MovieDetailsScreen : Screen("movie_details_screen")
}

