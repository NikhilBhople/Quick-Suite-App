package nikhil.bhople.quicksuiteapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import nikhil.bhople.quicksuiteapp.data.data_source.remote.AssetManager
import nikhil.bhople.quicksuiteapp.data.data_source.local.MovieDao
import nikhil.bhople.quicksuiteapp.data.data_source.local.MovieDataBase
import nikhil.bhople.quicksuiteapp.data.data_source.remote.RetrofitMovieDataSource
import nikhil.bhople.quicksuiteapp.data.repository.MovieRepositoryImpl
import nikhil.bhople.quicksuiteapp.domain.repository.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://www.quicksuitmovies.com"

    @Provides
    @Singleton
    fun provideRetrofitMovieDataSource(): RetrofitMovieDataSource {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RetrofitMovieDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context): MovieDataBase {
        return Room.databaseBuilder(
            context, MovieDataBase::class.java,
            "movie_app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(dataBase: MovieDataBase): MovieDao {
        return dataBase.movieDao
    }


    @Provides
    fun provideAssetManager(
        @ApplicationContext context: Context,
        @IoDispatcher dispatcher: CoroutineDispatcher

    ): AssetManager {
        return AssetManager(context, dispatcher)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: RetrofitMovieDataSource,
        movieDao: MovieDao,
        assetManager: AssetManager,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): MovieRepository {
        return MovieRepositoryImpl(api, movieDao, assetManager, dispatcher)
    }
}