package com.example.repository

import com.example.model.ApiResponse
import com.example.model.Ninja

interface NinjaRepositoryAlt {

    val ninjas : List<Ninja>

    suspend fun getNinjas(page : Int, limit : Int): ApiResponse
    suspend fun searchNinjas(query : String?): ApiResponse
}