package com.example.routes

import com.example.model.ApiResponse
import com.example.repository.NinjaRepositoryAlt
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.getNinjasAlt() {
    val ninjaRepositoryAlt: NinjaRepositoryAlt by inject()

    get("/boruto/ninjas") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            val limit = call.request.queryParameters["limit"]?.toInt() ?: 3
            require(page in 1..5)

            val apiResponse = ninjaRepositoryAlt.getNinjas(
                page,
                limit
            )

            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Invalid page number"
                ),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
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