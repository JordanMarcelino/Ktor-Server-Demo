package com.example.plugins

import com.example.di.repositoryModule
import io.ktor.application.*
import org.koin.core.logger.Level
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.modules
import org.koin.logger.slf4jLogger

fun Application.configureKoin(){
    install(Koin){
        slf4jLogger(Level.ERROR)
        modules(
            repositoryModule
        )
    }
}