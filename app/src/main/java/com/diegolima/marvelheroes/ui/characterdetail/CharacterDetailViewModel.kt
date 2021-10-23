package com.diegolima.marvelheroes.ui.characterdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegolima.marvelheroes.core.network.NetworkResponse
import com.diegolima.marvelheroes.domain.models.MarvelResponse
import com.diegolima.marvelheroes.domain.network.MarvelCharacterRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailViewModel(
    private val marvelCharacterRepository: MarvelCharacterRepository
) : ViewModel(){
    val responseLiveData = MutableLiveData<NetworkResponse<MarvelResponse>>()

    fun getCharacterDetail(id: Int){
        responseLiveData.postValue(NetworkResponse.loading())

        marvelCharacterRepository.getCharacter(id).enqueue(object : Callback<MarvelResponse>{
            override fun onResponse(
                call: Call<MarvelResponse>,
                response: Response<MarvelResponse>
            ) {
                if(response.isSuccessful){
                    responseLiveData.value = NetworkResponse.sucess(response.body())
                    return
                }
            }

            override fun onFailure(call: Call<MarvelResponse>, t: Throwable) {
                responseLiveData.value = NetworkResponse.error(t)
            }

        })
    }
}