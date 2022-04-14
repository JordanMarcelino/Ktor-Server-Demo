package com.example.plugins

import io.ktor.features.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import java.time.Duration

fun Application.configureHTTP() {
    install(DefaultHeaders) {
        val oneYearInSeconds = Duration.ofDays(365).seconds
        header(HttpHeaders.CacheControl, "public, max-age=$oneYearInSeconds, immutable")
    }

}
