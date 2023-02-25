package com.example.flowdemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowdemo.ui.main.usecases.ObserveOnSportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSportsUseCase: ObserveOnSportsUseCase
) : ViewModel() {

    fun initialize() {
        subscribeToSportsUpdates()
        collectSportsUpdates()

        //For testing purpose
        test()
    }

    private fun subscribeToSportsUpdates() {
        viewModelScope.launch {
            getSportsUseCase.subscribe(this)
            println("DBG: VIEWMODEL SUBSCRIBED TO SPORTS USECASE")
        }
    }

    private fun unsubscribeFromSportsUpdates() {
        viewModelScope.launch {
            getSportsUseCase.unsubscribe()
            println("DBG: VIEWMODEL UNSUBSCRIBED FROM SPORTS USECASE")
        }
    }

    private fun collectSportsUpdates() {
        viewModelScope.launch {
            getSportsUseCase.sportsFlow.collect {
                println("DBG: VIEWMODEL COLLECTED SPORTS: $it")
            }
        }
    }

    private fun test() {
        viewModelScope.launch {
            //unsubscribe after x time
            delay(10000)
            unsubscribeFromSportsUpdates()
            //re-subscribe after x time
            delay(5000)
            subscribeToSportsUpdates()
        }
    }
}