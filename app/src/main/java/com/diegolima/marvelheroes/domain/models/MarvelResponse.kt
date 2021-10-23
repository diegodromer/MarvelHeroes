package com.diegolima.marvelheroes.domain.models

import com.google.gson.annotations.SerializedName

data class MarvelResponse(
    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val marvelData: MarvelData
)