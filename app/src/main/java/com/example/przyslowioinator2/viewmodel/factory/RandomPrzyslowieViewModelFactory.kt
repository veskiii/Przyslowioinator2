package com.example.przyslowioinator2.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.przyslowioinator2.repository.PrzyslowiaRepository
import com.example.przyslowioinator2.viewmodel.RandomPrzyslowieViewModel

class RandomPrzyslowieViewModelFactory(
    private val repository: PrzyslowiaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RandomPrzyslowieViewModel(repository) as T
    }
}