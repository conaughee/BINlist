package com.conaguhee.binlist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.conaguhee.binlist.dao.BinDao
import com.conaguhee.binlist.entity.BinEntity

@Database(entities = [BinEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun binDao(): BinDao
}