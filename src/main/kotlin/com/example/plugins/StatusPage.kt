package com.example.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.configureStatusPage(){
    install(StatusPages){
        status(HttpStatusCode.NotFound){
            call.respondText("Page Not Found", ContentType.Text.Plain, HttpStatusCode.NotFound)
        }
        status(HttpStatusCode.InternalServerError){
            call.respondText("Internal Server Error", ContentType.Text.Plain, HttpStatusCode.InternalServerError)
        }
    }
}