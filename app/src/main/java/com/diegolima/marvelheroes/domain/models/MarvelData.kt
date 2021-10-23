package com.diegolima.marvelheroes.domain.models

import com.google.gson.annotations.SerializedName

data class MarvelData(
    @SerializedName("total") val total: Int,
    @SerializedName("results") val results: List<Character>
)