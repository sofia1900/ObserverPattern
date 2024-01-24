package com.sofia.observerpattern.domain

class GetDogUseCase {
    fun getDog(): Dog {
        return Dog("1", "Luna", "14")
    }
}