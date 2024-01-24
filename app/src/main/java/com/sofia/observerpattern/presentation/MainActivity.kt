package com.sofia.observerpattern.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.sofia.observerpattern.R
import com.sofia.observerpattern.data.HeroDataRepository
import com.sofia.observerpattern.data.remote.HeroRemoteDataSource
import com.sofia.observerpattern.databinding.ActivityMainBinding
import com.sofia.observerpattern.domain.GetAllSuperHeroesUseCase
import com.sofia.observerpattern.observer.Observer

//Suscriptor concreto
class MainActivity : AppCompatActivity(), Observer {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        MainViewModel(GetAllSuperHeroesUseCase(HeroDataRepository(HeroRemoteDataSource())))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }

    private fun bind(){
        viewModel.addObserver(this)
        viewModel.getDog()
    }

    override fun update() {
        viewModel.uiState.apply {
            if (this.isLoading){
                binding.skeletonLayout.showSkeleton()
            }else{
                binding.skeletonLayout.showOriginal()
                if (this.error != null){
                    Snackbar.make(binding.root, getString(R.string.snackbar_error), Snackbar.LENGTH_SHORT).show()
                }else{
                    hero?.apply {
                        binding.nameHero.text = hero.name
                    }
                }
            }
        }
    }
}