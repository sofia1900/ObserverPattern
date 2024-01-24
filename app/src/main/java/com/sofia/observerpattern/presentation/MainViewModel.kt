package com.sofia.observerpattern.presentation

import androidx.lifecycle.ViewModel
import com.sofia.observerpattern.domain.Dog
import com.sofia.observerpattern.domain.GetDogUseCase
import com.sofia.observerpattern.observer.Observer
import com.sofia.observerpattern.observer.Subject

class MainViewModel (private val useCase: GetDogUseCase): ViewModel(), Subject {

    //Servicio a observar - generador de eventos - es lo que cambia

    private val observers = mutableListOf<Observer>()
    var uiState : UiState = UiState()

    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }
    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }
     override fun notifyObservers(){
        for (observer in observers) {
            observer.update()
        }
    }

    fun getDog() {
        val dog = useCase.getDog()
        uiState = UiState(dog = dog)
        notifyObservers()
    }

    data class UiState (
        val isLoading : Boolean = false,
        val isError : Boolean = false,
        val dog : Dog? = null
    )
}