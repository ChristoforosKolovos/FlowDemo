package com.example.flowdemo.ui.main.repositories

import kotlinx.coroutines.flow.Flow

interface SportsRepository {

    suspend fun getSports(legacy : Boolean): Flow<Int>

}