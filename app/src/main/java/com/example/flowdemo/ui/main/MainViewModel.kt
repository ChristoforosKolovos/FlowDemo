package com.example.flowdemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowdemo.ui.main.usecases.ObserveOnSportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSportsUseCase: ObserveOnSportsUseCase
) : ViewModel() {
    
    fun initialize() {
        viewModelScope.launch {
            getSportsUseCase.subscribe(this)
            getSportsUseCase.sportsFlow.collect {
                println("DBG: VIEWMODEL COLLECTED SPORTS: $it")
            }
        }
    }
}