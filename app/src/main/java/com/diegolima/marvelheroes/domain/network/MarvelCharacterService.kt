package com.diegolima.marvelheroes.domain.network

import com.diegolima.marvelheroes.domain.models.MarvelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelCharacterService {

    @GET("/v1/public/characters")
    fun getCharacters(): Call<MarvelResponse>

    @GET("/v1/public/characters/{id}")
    fun getCharacter(@Path("id") characterId: Int): Call<MarvelResponse>
}