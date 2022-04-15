package com.example.plugins

import com.example.routes.getNinjas
import com.example.routes.root
import com.example.routes.searchNinjas
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*

fun Application.configureRouting() {


    routing {
        root()
        getNinjas()
        searchNinjas()

        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static/image")
        }

    }
}
