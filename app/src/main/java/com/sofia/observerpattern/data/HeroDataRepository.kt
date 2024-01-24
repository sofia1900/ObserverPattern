package com.sofia.observerpattern.data

import com.sofia.observerpattern.app.Either
import com.sofia.observerpattern.app.ErrorApp
import com.sofia.observerpattern.data.remote.HeroRemoteDataSource
import com.sofia.observerpattern.domain.Hero
import com.sofia.observerpattern.domain.HeroRepository

class HeroDataRepository(private val remoteDataSource: HeroRemoteDataSource) : HeroRepository {
    override suspend fun findHeroes(): Either<ErrorApp, List<Hero>> {
        return remoteDataSource.findAllSuperHeroes()
    }
}