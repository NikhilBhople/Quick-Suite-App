package nikhil.bhople.quicksuiteapp.data.data_source.remote

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import nikhil.bhople.quicksuiteapp.data.dto.MovieDto
import javax.inject.Inject

class AssetManager @Inject constructor(
    @ApplicationContext context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val SERVICE_LATENCY_IN_MILLIS = 2000L
    private val LOCAL_DATA_JSON = "movie_list.json"
    private val assetManager = context.assets
    private val inputStream = assetManager.open(LOCAL_DATA_JSON)

    suspend fun getMovieListFromLocalJson(): List<MovieDto> = withContext(ioDispatcher) {
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        delay(SERVICE_LATENCY_IN_MILLIS)
        return@withContext Gson().fromJson(jsonString, Array<MovieDto>::class.java).toMutableList()
    }
}