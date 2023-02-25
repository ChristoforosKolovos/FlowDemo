package com.example.flowdemo.ui.main.data_sources

import kotlinx.coroutines.flow.Flow

interface SportsDataSource {

    suspend fun getSports() : Flow<Int>

}