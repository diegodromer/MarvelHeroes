package com.diegolima.marvelheroes.core.network

data class NetworkResponse<out T> (
    val status: Status,
    val data: T?,
    val message: String?
) {

    companion object{

        fun <T> sucess(data: T?): NetworkResponse<T>{
            return NetworkResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(e: Throwable?): NetworkResponse<T>{
            return NetworkResponse(Status.ERROR, null, e?.message)
        }

        fun <T> loading(): NetworkResponse<T>{
            return NetworkResponse(Status.LOADING, null, null)
        }
    }

    enum class Status{
        SUCCESS, ERROR, LOADING
    }
}