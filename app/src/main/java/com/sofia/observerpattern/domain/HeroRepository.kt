package com.sofia.observerpattern.domain

import com.sofia.observerpattern.app.Either
import com.sofia.observerpattern.app.ErrorApp

interface HeroRepository {
    suspend fun findHeroes(): Either<ErrorApp, List<Hero>>
}