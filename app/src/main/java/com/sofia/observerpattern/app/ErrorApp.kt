package com.sofia.observerpattern.app

sealed class ErrorApp {
    object UnknownError : ErrorApp()

}