package com.example.przyslowioinator2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.przyslowioinator2.model.Przyslowie
import com.example.przyslowioinator2.repository.PrzyslowiaRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RandomPrzyslowieViewModel(private val repository: PrzyslowiaRepository) : ViewModel() {
    val randomPrzyslowie: MutableLiveData<Response<Przyslowie>> = MutableLiveData()

    fun getRandomPrzyslowie() {
        viewModelScope.launch {
            val response = repository.getRandomPrzyslowie()
            randomPrzyslowie.value = response
        }
    }
}