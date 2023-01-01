package com.conaguhee.binlist.hiltModules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.conaguhee.binlist.api.ApiService
import com.conaguhee.binlist.api.okhttp
import com.conaguhee.binlist.api.retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService = retrofit(okhttp()).create(ApiService::class.java)
}