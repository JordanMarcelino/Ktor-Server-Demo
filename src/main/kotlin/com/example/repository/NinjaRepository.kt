package com.example.repository

import com.example.model.ApiResponse
import com.example.model.Ninja

interface NinjaRepository {

    val ninjas: Map<Int, List<Ninja>>

    val page1: List<Ninja>
    val page2: List<Ninja>
    val page3: List<Ninja>
    val page4: List<Ninja>
    val page5: List<Ninja>

    suspend fun getNinjas(page : Int): ApiResponse
    suspend fun searchNinjas(query : String?): ApiResponse
}