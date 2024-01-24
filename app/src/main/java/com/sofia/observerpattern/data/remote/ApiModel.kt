package com.sofia.observerpattern.data.remote

import com.google.gson.annotations.SerializedName

data class HeroApiModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)