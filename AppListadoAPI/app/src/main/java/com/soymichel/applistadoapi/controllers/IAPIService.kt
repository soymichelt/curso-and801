package com.soymichel.applistadoapi.controllers

import com.soymichel.applistadoapi.models.Personajes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IAPIService {
    @GET
    fun getCharacters(@Url url: String): Call<Personajes>
}