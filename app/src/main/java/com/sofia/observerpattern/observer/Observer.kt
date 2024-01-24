package com.sofia.observerpattern.observer

import com.sofia.observerpattern.presentation.MainViewModel
import java.util.Objects

//Notifica a los suspcriptores

interface Observer {
    fun update() //se llama cuando el estado del sujeto ha cambiado
}
