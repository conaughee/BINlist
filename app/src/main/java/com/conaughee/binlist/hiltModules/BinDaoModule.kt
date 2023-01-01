package com.conaguhee.binlist.hiltModules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.conaguhee.binlist.dao.BinDao
import com.conaguhee.binlist.db.AppDb

@InstallIn(SingletonComponent::class)
@Module
object BinDaoModule {
    @Provides
    fun provideBinDao(db: AppDb): BinDao = db.binDao()
}