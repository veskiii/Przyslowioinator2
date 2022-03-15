package com.example.przyslowioinator2.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.przyslowioinator2.repository.PrzyslowiaRepository
import com.example.przyslowioinator2.viewmodel.PrzyslowiaViewModel

class PrzyslowiaViewModelFactory(
    private val repository: PrzyslowiaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PrzyslowiaViewModel(repository) as T
    }
}