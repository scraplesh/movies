package me.scraplesh.module.domain.entities

data class BriefMovieEntity(
  val title: String,
  val year: String,
  val imdbId: String,
  val posterUrl: String
) : Entity