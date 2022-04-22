package com.example.di

import com.example.repository.NinjaRepository
import com.example.repository.NinjaRepositoryAlt
import com.example.repository.NinjaRepositoryAltImpl
import com.example.repository.NinjaRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NinjaRepository>{ NinjaRepositoryImpl() }
    single<NinjaRepositoryAlt>{ NinjaRepositoryAltImpl() }
}