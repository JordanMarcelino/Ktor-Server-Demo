package com.example.routes

import com.example.model.ApiResponse
import com.example.repository.NinjaRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.getNinjas(){
    val repository : NinjaRepository by inject()

    get("/boruto/ninjas") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..5)

            val apiResponse = repository.getNinjas(page)

            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        }catch (e : NumberFormatException){
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Invalid page number"
                ),
                status = HttpStatusCode.BadRequest
            )
        }catch (e : IllegalArgumentException){
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Ninja not found"
                ),
                status = HttpStatusCode.NotFound
            )
        }
    }
}