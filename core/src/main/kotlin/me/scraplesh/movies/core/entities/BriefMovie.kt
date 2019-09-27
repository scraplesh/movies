package me.scraplesh.movies.core.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.scraplesh.movies.domain.entities.BriefMovieEntity

@Parcelize
data class BriefMovie(
  val title: String,
  val year: String,
  val type: String,
  val imdbId: String,
  val posterUrl: String
) :
  Parcelable,
  Mapping<BriefMovieEntity> {

  constructor(movie: BriefMovieEntity) : this(
    title = movie.title,
    year = movie.year,
    type = movie.type,
    imdbId = movie.imdbId,
    posterUrl = movie.posterUrl
  )

  override val entity: BriefMovieEntity
    get() = BriefMovieEntity(title, year, type, imdbId, posterUrl)

}