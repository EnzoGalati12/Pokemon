package com.example.pokemon.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private val client: OkHttpClient = OkHttpClient.Builder().build()
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun createPokeApiService(): PokeApiService {
        return retrofit.create(PokeApiService::class.java)
    }
}