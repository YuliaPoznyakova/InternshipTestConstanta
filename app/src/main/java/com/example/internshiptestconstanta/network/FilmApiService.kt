package com.example.internshiptestconstanta.network

import com.example.internshiptestconstanta.model.Films
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://raw.githubusercontent.com/constanta-android-dev/intership-wellcome-task/main/"

var gson = GsonBuilder()
    .create()

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

interface FilmApiService {
    @GET("films.json")
    suspend fun getFilms(): Response<Films>
}

object FilmApi {
    val retrofitService : FilmApiService by lazy {
        retrofit.create(FilmApiService::class.java)
    }
}



