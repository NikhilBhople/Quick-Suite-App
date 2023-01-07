package nikhil.bhople.quicksuiteapp.data.data_source.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieDaoTest {

    private lateinit var movieDao: MovieDao
    private lateinit var movieDataBase: MovieDataBase

    @Before
    fun setUp() {
        movieDataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), MovieDataBase::class.java
        ).build()
        movieDao = movieDataBase.movieDao
    }

    @After
    fun tearDown() {
        movieDataBase.close()
    }

    @Test
    fun addMoviesToDatabase_should_add_the_movieDetails_list_to_database() = runBlocking {
        val movieList = getMovieList()
        movieDao.addMoviesToDatabase(movieList)
        val movieDetails = movieDao.getMovieList().first()
        assert(movieList.contains(movieDetails[0]))
    }

    @Test
    fun count_should_return_0_when_no_entries_in_database() = runBlocking {
        assert(movieDao.count() == 0)
    }

    @Test
    fun count_should_return_the_count_of_database_entries() = runBlocking {
        movieDao.addMoviesToDatabase(getMovieList())
        assert(movieDao.count() == 1)
    }

    @Test
    fun getMovieDetailsStream_should_return_movieDetails_flow() = runBlocking {
        movieDao.addMoviesToDatabase(getMovieList())
        val movieDetails = movieDao.getMovieDetailsStream(1).first()
        assert(getMovieList()[0] == movieDetails)
    }

    @Test
    fun getMovieDetails_should_return_movieDetails() = runBlocking {
        movieDao.addMoviesToDatabase(getMovieList())
        val movieDetails = movieDao.getMovieDetails(1)
        assert(getMovieList()[0] == movieDetails)
    }

    @Test
    fun updateMovieWatchList_should_update_movieDetails() = runBlocking {
        val movieList = getMovieList()
        movieDao.addMoviesToDatabase(movieList)
        val isUpdated = movieDao.updateMovieWatchList(
            movieList[0].copy(isAddedToWatchList = movieList[0].isAddedToWatchList.not())
        )
        assert(isUpdated == 1)
        val movieDetails = movieDao.getMovieDetails(1)
        assert(movieDetails.isAddedToWatchList != movieList[0].isAddedToWatchList)
    }

    private fun getMovieList(): MutableList<MovieDetails> {
        val movieList = mutableListOf<MovieDetails>()
        movieList.add(
            MovieDetails(
                id = 1,
                description = "test desc",
                duration = "1h 23m",
                genre = "Action,Drama",
                isAddedToWatchList = false,
                title = "title",
                trailerLink = "",
                releasedDate = "12 March 2022",
                rating = 7.1,
                poster = "poster/link"
            )
        )
        return movieList
    }

}