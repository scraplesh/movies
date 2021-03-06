package me.scraplesh.movies.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

abstract class MviCoreView<UiEvent, State> private constructor(
    protected val uiEvents: Relay<UiEvent>,
    protected val states: Relay<State>,
    protected val disposeBag: CompositeDisposable
) :
    Consumer<State> by states,
    ObservableSource<UiEvent> by uiEvents,
    Disposable by disposeBag {

  constructor() : this(
      uiEvents = PublishRelay.create(),
      states = BehaviorRelay.create(),
      disposeBag = CompositeDisposable()
  )

  private var view: View? = null

  fun getView(inflater: LayoutInflater, container: ViewGroup?): View =
      view ?: createView(inflater, container).also { view = it }

  abstract fun createView(inflater: LayoutInflater, container: ViewGroup?): View
}
