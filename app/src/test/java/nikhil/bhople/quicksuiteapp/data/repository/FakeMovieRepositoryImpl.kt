package nikhil.bhople.quicksuiteapp.data.repository

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nikhil.bhople.quicksuiteapp.data.common.Resource
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.toMovie
import nikhil.bhople.quicksuiteapp.data.dto.MovieDto
import nikhil.bhople.quicksuiteapp.data.dto.toMovieDetails
import nikhil.bhople.quicksuiteapp.domain.model.Movie
import nikhil.bhople.quicksuiteapp.domain.repository.MovieRepository

class FakeMovieRepositoryImpl : MovieRepository {

    private val movieList = mutableListOf<MovieDetails>()

    override fun getMovieList(): Flow<Resource<List<Movie>>> {
        return flow { emit(Resource.Success(movieList.map { it.toMovie() })) }
    }

    override fun getMovieDetailsStream(movieId: Int): Flow<Resource<MovieDetails>> {
        movieList.forEach {
            if (it.id == movieId) {
                return flow { emit(Resource.Success(it)) }
            }
        }
        return flow { emit(Resource.Failure("Not Found")) }
    }

    override suspend fun updateMovieWatchList(movieId: Int) {
        movieList.forEachIndexed { index, movieDetails ->
            if (movieDetails.id == movieId){
                movieList[index] = movieDetails.copy(isAddedToWatchList = movieDetails.isAddedToWatchList.not())
                return
            }
        }
    }

    override suspend fun loadJsonValuesToDatabase() {
        val movieListFromJson = Gson().fromJson(jsonString, Array<MovieDto>::class.java).map { it.toMovieDetails() }
        movieList.addAll(movieListFromJson)
    }


    private val jsonString = "[\n" +
            "  {\n" +
            "    \"id\":1,\n" +
            "    \"title\":\"Tenet\",\n" +
            "    \"description\":\"Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.\",\n" +
            "    \"rating\":7.8,\n" +
            "    \"duration\":150,\n" +
            "    \"poster\":\"poster_tenet\",\n" +
            "    \"genre\":[\"Action\", \"Sci-Fi\"],\n" +
            "    \"released_date\":\"3 September 2020\",\n" +
            "    \"trailer_link\":\"https://www.youtube.com/watch?v=LdOM0x0XDMo\",\n" +
            "    \"isAddedToWatchList\":false\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":2,\n" +
            "    \"title\":\"Spider-Man: Into the Spider-Verse\",\n" +
            "    \"description\":\"Teen Miles Morales becomes the Spider-Man of his universe, and must join with five spider-powered individuals from other dimensions to stop a threat for all realities.\",\n" +
            "    \"rating\":8.4,\n" +
            "    \"duration\":117,\n" +
            "    \"poster\":\"poster_spider_man\",\n" +
            "    \"genre\":[\"Action\", \"Animation\", \"Adventure\"],\n" +
            "    \"released_date\":\"14 December 2018\",\n" +
            "    \"trailer_link\":\"https://www.youtube.com/watch?v=tg52up16eq0\",\n" +
            "    \"isAddedToWatchList\":true\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":3,\n" +
            "    \"title\":\"Knives Out\",\n" +
            "    \"description\":\"A detective investigates the death of a patriarch of an eccentric, combative family.\",\n" +
            "    \"rating\":7.9,\n" +
            "    \"duration\":130,\n" +
            "    \"poster\":\"poster_knives_out\",\n" +
            "    \"genre\":[\"Comedy\", \"Crime\", \"Drama\"],\n" +
            "    \"released_date\":\"27 November 2019\",\n" +
            "    \"trailer_link\":\"https://www.youtube.com/watch?v=qGqiHJTsRkQ\",\n" +
            "    \"isAddedToWatchList\":true\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":4,\n" +
            "    \"title\":\"Guardians of the Galaxy\",\n" +
            "    \"description\":\"A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.\",\n" +
            "    \"rating\":8.0,\n" +
            "    \"duration\":121,\n" +
            "    \"poster\":\"poster_guardians_of_the_galaxy\",\n" +
            "    \"genre\":[\"Action\", \"Adventure\", \"Comedy\"],\n" +
            "    \"released_date\":\"1 August 2014\",\n" +
            "    \"trailer_link\":\"https://www.youtube.com/watch?v=d96cjJhvlMA\",\n" +
            "    \"isAddedToWatchList\":false\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":5,\n" +
            "    \"title\":\"Avengers: Age of Ultron\",\n" +
            "    \"description\":\"When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron, things go horribly wrong and it's up to Earth's mightiest heroes to stop the villainous Ultron from enacting his terrible plan.\",\n" +
            "    \"rating\":7.3,\n" +
            "    \"duration\":141,\n" +
            "    \"poster\":\"poster_avengers_age_of_ultron\",\n" +
            "    \"genre\":[\"Action\", \"Adventure\", \"Sci-Fi\"],\n" +
            "    \"released_date\":\"1 May 2015\",\n" +
            "    \"trailer_link\":\"https://www.youtube.com/watch?v=tmeOjFno6Do\",\n" +
            "    \"isAddedToWatchList\":true\n" +
            "  }\n" +
            "]"
}