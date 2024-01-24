package com.sofia.observerpattern.domain

import com.sofia.observerpattern.app.Either
import com.sofia.observerpattern.app.ErrorApp

class GetAllSuperHeroesUseCase(private val heroRepository: HeroRepository) {
    suspend operator fun invoke(): Either<ErrorApp, List<Hero>> {
        return heroRepository.findHeroes()
    }
}