package com.sofia.observerpattern.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofia.observerpattern.app.ErrorApp
import com.sofia.observerpattern.domain.GetAllSuperHeroesUseCase
import com.sofia.observerpattern.domain.Hero
import com.sofia.observerpattern.observer.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Nofificador.
class MainViewModel(private val useCase: GetAllSuperHeroesUseCase) : ViewModel() {

    //Estado que va cambiando
    var uiState: UiState = UiState()

    //Lista de observadores/suscriptores
    private val observers = mutableListOf<Observer>()

    //Acción que provoca el cambio y lo notifica
    fun getHero() {
        responseLoading()
        viewModelScope.launch(Dispatchers.IO) {
            useCase().fold(
                { responseError(it) },
                { responseSuccess(it.first()) }
            )
        }
    }

    private fun responseLoading() {
        uiState = UiState(isLoading = true)
        notifyObservers()
    }

    private fun responseError(error: ErrorApp) {
        uiState = UiState(error = error)
        notifyObservers()
    }

    private fun responseSuccess(hero: Hero) {
        uiState = UiState(hero = hero)
        notifyObservers()
    }

    /* Notificar a los suscriptores en el hilo principal
    para que la vista se pueda actualizar */
    private fun notifyObservers() {
        viewModelScope.launch {
            for (observer in observers) {
                observer.update()
            }
        }
    }

    //Suscribirse
    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    //Eliminar la suscripcion
    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val hero: Hero? = null
    )
}