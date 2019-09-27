package me.scraplesh.module.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import me.scraplesh.module.BuildConfig
import me.scraplesh.module.data.datasources.ImdbWebApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
  @Singleton
  @Provides
  fun context(): Context = context

  @Singleton
  @Provides
  fun imdbWebApi(): ImdbWebApi = Retrofit.Builder()
    .baseUrl(BuildConfig.imdbApiUrl)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
    .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
    .build()
    .create(ImdbWebApi::class.java)
}