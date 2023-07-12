package com.ataerdal.apptern201homework.core

sealed class Response<out T> {
    data class Success<T>(val data: T?) : Response<T>()
    data class Error<T>(val message: String?, val data: T? = null) : Response<T>()
    object Loading : Response<Nothing>()
}