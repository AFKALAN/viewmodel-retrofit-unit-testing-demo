package com.example.viewmodelretrofittesting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(val apiService: ApiService): ViewModel() {
    val resultLiveData = MutableLiveData<SearchArtistResponse>()
    val errorLiveData = MutableLiveData<String>()

    fun getData(query: String){
        viewModelScope.launch(IO){
            try {
                resultLiveData.postValue(apiService.getSearch(query).body())
            }catch (e: Exception){
                errorLiveData.postValue(e.message)
            }
        }
    }
}