package com.example.di

import com.example.repository.NinjaRepository
import com.example.repository.NinjaRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NinjaRepository>{ NinjaRepositoryImpl() }
}