package com.example.przyslowioinator2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.przyslowioinator2.model.Przyslowie
import com.example.przyslowioinator2.repository.PrzyslowiaRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PrzyslowiaViewModel(private val repository: PrzyslowiaRepository) : ViewModel() {
    val przyslowia: MutableLiveData<Response<List<Przyslowie>>> = MutableLiveData()

    fun getPrzyslowia() {
        viewModelScope.launch {
            val response = repository.getPrzyslowia()
            przyslowia.value = response
        }
    }
}