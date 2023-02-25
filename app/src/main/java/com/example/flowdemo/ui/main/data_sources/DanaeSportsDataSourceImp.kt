package com.example.flowdemo.ui.main.data_sources

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DanaeSportsDataSourceImp : SportsDataSource {
    override suspend fun getSports(): Flow<Int> {
        return dummyFlow()
    }

    private fun dummyFlow(): Flow<Int> {
        return flow {
            var counter = 0
            while (true) {
                counter++
                emit(counter)
                delay(1000)
            }
        }
    }

}