package nikhil.bhople.quicksuiteapp.data.data_source.remote

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import nikhil.bhople.quicksuiteapp.MainCoroutineRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AssetManagerTest {
    private lateinit var assetManger: AssetManager

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        assetManger = AssetManager(
            ApplicationProvider.getApplicationContext(),
            Dispatchers.Main
        )
    }

    @Test
    fun testAssetManagerFetchTheDataCorrectly() = runTest {
        val data = assetManger.getMovieListFromLocalJson()
        assertTrue(data.isNotEmpty())
    }


}