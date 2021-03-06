package me.scraplesh.features.movies

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import me.scraplesh.features.movies.MoviesFeature.Wish
import me.scraplesh.features.movies.MoviesView.UiEvent
import me.scraplesh.movies.navigation.NavigationEvent
import me.scraplesh.movies.navigation.RootCoordinator

class MoviesBindings(
    lifecycleOwner: LifecycleOwner,
    private val feature: MoviesFeature,
    coordinator: RootCoordinator
) : AndroidBindings<MoviesView>(lifecycleOwner) {

  init {
    binder.bind(feature.news to coordinator using { news ->
      when (news) {
        is MoviesFeature.News.MovieSelected -> NavigationEvent.ShowMovieScreen(news.movie)
      }
    })
  }

  override fun setup(view: MoviesView) {
    binder.bind(feature to view using { state ->
      MoviesView.ViewModel(
          movies = state.movies,
          isLoading = state.isLoading,
          noContent = !state.isLoading && state.movies.isEmpty() && state.error == null,
          error = state.error
      )
    })
    binder.bind(view to feature using { uiEvent ->
      when (uiEvent) {
        UiEvent.Retry -> Wish.RetryLoadingMovies
        is UiEvent.MovieSelected -> Wish.ShowMovie(uiEvent.movie)
      }
    })
  }
}