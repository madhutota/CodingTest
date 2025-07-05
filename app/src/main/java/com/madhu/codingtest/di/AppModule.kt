package com.madhu.codingtest.di

import com.madhu.codingtest.data.remote.RemoteApi
import com.madhu.codingtest.data.repository.RemoteRepoImpl
import com.madhu.codingtest.domain.repository.RemoteRepo
import com.madhu.codingtest.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUniversityApi(): RemoteApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUniversityRepo(api: RemoteApi): RemoteRepo {
        return RemoteRepoImpl(api = api)
    }


}