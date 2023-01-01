package com.conaguhee.binlist.api

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.conaguhee.binlist.dto.CardData
import com.conaughee.binlist.BuildConfig

fun okhttp(): OkHttpClient = OkHttpClient.Builder().build()

fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BuildConfig.BASE_URL)
    .client(client)
    .build()

interface ApiService {
    @GET("{cardNumber}")
    suspend fun get(@Path("cardNumber") cardNumber: Int): Response<CardData>
}