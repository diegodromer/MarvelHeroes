package com.diegolima.marvelheroes.ui.characterlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegolima.marvelheroes.core.network.NetworkResponse
import com.diegolima.marvelheroes.domain.models.Character
import com.diegolima.marvelheroes.domain.models.MarvelResponse
import com.diegolima.marvelheroes.domain.network.MarvelCharacterRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CharacterListViewModel (
    private val marvelCharacterRepository: MarvelCharacterRepository
) : ViewModel(){

    val responseLiveData = MutableLiveData<NetworkResponse<MarvelResponse>>()

    fun getCharacters() {
        responseLiveData.postValue(NetworkResponse.loading())

        marvelCharacterRepository.getCharacters().enqueue(object : Callback<MarvelResponse>{
            override fun onResponse(
                call: Call<MarvelResponse>,
                response: Response<MarvelResponse>
            ) {
                if (response.isSuccessful){
                    responseLiveData.value = NetworkResponse.sucess(response.body())
                    return
                }
            }

            override fun onFailure(call: Call<MarvelResponse>, t: Throwable) {
                responseLiveData.value = NetworkResponse.error(t)
            }
        })
    }

    fun filterCharactersList(charactersList: List<Character>, adapter: CharacterListAdapter, query: String) {
        val filteredList = charactersList.filter { character ->
            character.name.toLowerCase(Locale.getDefault()).contains(query.toLowerCase(Locale.getDefault()))
        }

        adapter.filterCharactersList(filteredList)
    }
}