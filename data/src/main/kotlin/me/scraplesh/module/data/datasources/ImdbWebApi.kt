package me.scraplesh.module.data.datasources

import io.reactivex.Single
import me.scraplesh.module.data.BuildConfig
import me.scraplesh.module.data.entities.MovieData
import me.scraplesh.module.data.entities.SearchMoviesEnvelope
import retrofit2.http.GET
import retrofit2.http.Query

interface ImdbWebApi {
  @GET("?apikey=${BuildConfig.imdbApiKey}&r=json&type=movie")
  fun searchMovies(@Query("s") query: String): Single<SearchMoviesEnvelope>

  @GET("?apikey=${BuildConfig.imdbApiKey}&type=movie&r=json")
  fun getMovie(@Query("i") imdbId: String): Single<MovieData>
}
