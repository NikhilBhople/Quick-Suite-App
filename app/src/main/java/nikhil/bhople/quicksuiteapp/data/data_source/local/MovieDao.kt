package nikhil.bhople.quicksuiteapp.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails

@Dao
interface MovieDao {
    @Query("Select * from movie_details_table")
    fun getMovieList(): Flow<List<MovieDetails>>

    @Query("Select * from movie_details_table where id = :id")
    suspend fun getMovieDetails(id: Int): MovieDetails

    @Query("Select * from movie_details_table where id = :id")
    fun getMovieDetailsStream(id: Int): Flow<MovieDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMoviesToDatabase(movies: List<MovieDetails>)

    @Query("SELECT COUNT(*) FROM movie_details_table")
    suspend fun count(): Int

    @Update
    fun updateMovieWatchList(movieDetails: MovieDetails): Int //Should return 1 when success.
}