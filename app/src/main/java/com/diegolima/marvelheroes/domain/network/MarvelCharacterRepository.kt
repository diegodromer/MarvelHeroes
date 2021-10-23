package com.diegolima.marvelheroes.domain.network

import com.diegolima.marvelheroes.domain.models.MarvelResponse
import retrofit2.Call

class MarvelCharacterRepository(
    private val marvelCharacterService: MarvelCharacterService
) {
    fun getCharacters(): Call<MarvelResponse> = marvelCharacterService.getCharacters()

    fun getCharacter(id: Int): Call<MarvelResponse> = marvelCharacterService.getCharacter(id)
}