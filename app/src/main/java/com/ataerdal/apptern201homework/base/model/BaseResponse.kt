package com.ataerdal.apptern201homework.base.model

data class BaseResponse<T>(
    val message: String?,
    val result: List<T>?,
    val status: String?,
    val statusCode: Int?
)