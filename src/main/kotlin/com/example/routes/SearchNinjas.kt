package com.example.routes

import com.example.repository.NinjaRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.searchNinjas(){
    val ninjaRepositoryAlt : NinjaRepository by inject()
    get("/search/ninjas"){
        val query = call.request.queryParameters["query"]
        val ninjas = ninjaRepositoryAlt.searchNinjas(query)

        call.respond(
            message = ninjas,
            status = HttpStatusCode.OK
        )
    }
}