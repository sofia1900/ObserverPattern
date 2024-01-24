package com.sofia.observerpattern.data.remote

import com.sofia.observerpattern.domain.Hero

fun HeroApiModel.toModel () : Hero =
    Hero(this.id, this.name)