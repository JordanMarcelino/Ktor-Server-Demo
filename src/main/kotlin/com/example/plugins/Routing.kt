package com.example.plugins

import com.example.routes.getNinjasAlt
import com.example.routes.root
import com.example.routes.searchNinjas
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*

fun Application.configureRouting() {


    routing {
        root()
//        getNinjas()
        getNinjasAlt()
        searchNinjas()

        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static/image")
        }

    }
}
