package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success : Boolean,
    val message : String? = null,
    val prevPage : Int? = null,
    val nextPage : Int? = null,
    val ninjas : List<Ninja> = emptyList(),
    val lastUpdated : Long? = null
)
