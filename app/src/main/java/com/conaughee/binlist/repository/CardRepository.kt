package com.conaguhee.binlist.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import com.conaguhee.binlist.dto.CardBin
import com.conaguhee.binlist.dto.CardData

interface CardRepository {
    val binData: Flow<List<CardBin>>
    val cardData: StateFlow<CardData>
    suspend fun get(bin: Int, isInit: Boolean)
    suspend fun removeById(id: Long)
    suspend fun removeAll()
}