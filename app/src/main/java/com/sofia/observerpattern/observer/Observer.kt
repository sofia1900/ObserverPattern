package com.sofia.observerpattern.observer


//Interfaz suscriptora para la notificación
interface Observer {
    fun update() //se llama cuando el estado del sujeto ha cambiado
}
