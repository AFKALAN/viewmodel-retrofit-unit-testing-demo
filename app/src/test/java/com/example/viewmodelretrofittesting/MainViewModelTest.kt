package com.example.viewmodelretrofittesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import retrofit2.Response
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var searchResponseObserver: Observer<SearchArtistResponse>
    @Mock
    lateinit var errorObserver: Observer<String>
    @Mock
    lateinit var apiService: ApiService

    @Test
    fun `search artist success`(){
        runBlocking {
            val gson = Gson()
            val dummyResponse = Response.success(gson.fromJson(ApiResponses.successResponse, SearchArtistResponse::class.java))
            doReturn(dummyResponse).`when`(apiService).getSearch("madona")
            val mainViewModel = MainViewModel(apiService)
            mainViewModel.resultLiveData.observeForever(searchResponseObserver)
            mainViewModel.getData("madona")
            verify(apiService).getSearch("madona")
            searchResponseObserver.onChanged(dummyResponse.body())
            mainViewModel.resultLiveData.removeObserver(searchResponseObserver)
        }
    }

    @Test
    fun `test for failure`(){
        runBlocking {
            val exception = RuntimeException("Network not available")
            doReturn(exception).`when`(apiService).getSearch("madona")
            val mainViewModel = MainViewModel(apiService)
            mainViewModel.errorLiveData.observeForever(errorObserver)
            mainViewModel.getData("madona")
            verify(apiService).getSearch("madona")
            errorObserver.onChanged("Network not available")
            mainViewModel.errorLiveData.removeObserver(errorObserver)
        }
    }
}