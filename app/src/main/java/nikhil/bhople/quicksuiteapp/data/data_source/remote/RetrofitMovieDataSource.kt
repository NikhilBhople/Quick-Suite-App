package nikhil.bhople.quicksuiteapp.data.data_source.remote

import nikhil.bhople.quicksuiteapp.data.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMovieDataSource {

    // Mentioned dummy path endpoints as I don't have API

    @GET(value = "/v1/movies")
    suspend fun getMovieList(): List<MovieDto>

    @GET(value = "/v1/movie/details/{movieId}")
    suspend fun getMovieDetails(@Path("{movieId}") movieId: Int): MovieDto
}