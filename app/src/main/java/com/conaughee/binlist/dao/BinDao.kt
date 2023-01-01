package com.conaguhee.binlist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.conaguhee.binlist.entity.BinEntity

@Dao
interface BinDao {

    @Query("SELECT * FROM BinEntity ORDER BY id DESC")
    fun getAll(): Flow<List<BinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bin: BinEntity): Long

    @Query("DELETE FROM BinEntity WHERE id = :id")
    suspend fun removeById(id: Long)

    @Query("DELETE FROM BinEntity")
    suspend fun removeAll()
}