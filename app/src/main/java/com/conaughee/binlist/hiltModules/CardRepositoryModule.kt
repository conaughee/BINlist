package com.conaguhee.binlist.hiltModules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.conaguhee.binlist.repository.CardRepository
import com.conaguhee.binlist.repository.CardRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PostRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPostRepository(imp: CardRepositoryImpl): CardRepository
}