package nikhil.bhople.quicksuiteapp.data.common

import nikhil.bhople.quicksuiteapp.R

object AssetsUtils {
    // for loading images from server we can use Coil, Picasso or Glide

    private val drawables: Map<String, Int> = mapOf(
        "poster_tenet" to R.drawable.poster_tenet,
        "poster_spider_man" to R.drawable.poster_spider_man,
        "poster_knives_out" to R.drawable.poster_knives_out,
        "poster_guardians_of_the_galaxy" to R.drawable.poster_guardians_of_the_galaxy,
        "poster_avengers_age_of_ultron" to R.drawable.poster_avengers
    )

    fun getDrawableByName(name: String): Int? {
        return drawables[name]
    }
}