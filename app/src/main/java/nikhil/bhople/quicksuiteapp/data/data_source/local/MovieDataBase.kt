package nikhil.bhople.quicksuiteapp.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import nikhil.bhople.quicksuiteapp.data.data_source.local.entity.MovieDetails

@Database(entities = [MovieDetails::class], version = 1, exportSchema = false)
abstract class MovieDataBase: RoomDatabase() {
    abstract val movieDao: MovieDao
}