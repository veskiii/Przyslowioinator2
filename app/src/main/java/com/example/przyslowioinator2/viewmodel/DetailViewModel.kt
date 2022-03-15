package com.example.przyslowioinator2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.przyslowioinator2.model.Przyslowie
import com.example.przyslowioinator2.repository.PrzyslowiaRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel(private val repository: PrzyslowiaRepository) : ViewModel() {
    val przyslowie: MutableLiveData<Response<Przyslowie>> = MutableLiveData()

    fun getPrzyslowie(id : String) {
        viewModelScope.launch {
            val response = repository.getPrzyslowie(id)
            przyslowie.value = response
        }
    }
}