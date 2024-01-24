package com.sofia.observerpattern.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sofia.observerpattern.databinding.ActivityMainBinding
import com.sofia.observerpattern.domain.GetDogUseCase
import com.sofia.observerpattern.observer.Observer

class MainActivity : AppCompatActivity(), Observer {
    //Observador o suscriptor

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by lazy {
        MainViewModel(GetDogUseCase())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.addObserver(this)
        viewModel.getDog()
    }

    override fun update() {
        viewModel.uiState.apply {
            dog?.apply {
                binding.text.text = dog.name
            }
        }
    }
}