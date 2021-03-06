package me.scraplesh.features.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.functions.Consumer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movies.imageview_itemmovies
import kotlinx.android.synthetic.main.item_movies.textview_itemmovies_title
import kotlinx.android.synthetic.main.item_movies.textview_itemmovies_year
import me.scraplesh.movies.domain.entities.MovieEntity

class MoviesAdapter :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>(),
    Consumer<List<MovieEntity>>,
    ObservableSource<MovieEntity> {

  private var movies: List<MovieEntity> = emptyList()
  private val selectedMovies = PublishRelay.create<MovieEntity>()

  override fun getItemCount(): Int = movies.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
      LayoutInflater.from(parent.context)
          .inflate(R.layout.item_movies, parent, false)
  )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    movies[position].let { movie ->
      holder.apply {
        itemView.setOnClickListener { selectedMovies.accept(movie) }

        Glide.with(itemView)
          .load(movie.posterUrl)
          .into(imageview_itemmovies)

        textview_itemmovies_title.text = movie.title
        textview_itemmovies_year.text = movie.year
      }
    }
  }

  override fun accept(newItems: List<MovieEntity>) {
    movies = newItems
  }

  override fun subscribe(observer: Observer<in MovieEntity>) {
    selectedMovies.subscribe(observer)
  }

  class ViewHolder(override val containerView: View) :
      RecyclerView.ViewHolder(containerView),
      LayoutContainer

}
