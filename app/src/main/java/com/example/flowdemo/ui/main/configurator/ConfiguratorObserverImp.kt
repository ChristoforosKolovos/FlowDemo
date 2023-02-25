package com.example.flowdemo.ui.main.configurator

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ConfiguratorObserverImp : ConfiguratorObserver {

    override suspend fun observe(): Flow<Boolean> {
        return dummyFlow()
    }

    private suspend fun dummyFlow(): Flow<Boolean> {
        return flow {
            var flag = true
            while (true) {
                flag = !flag
                emit(flag)
                delay(5000)
            }
        }
    }
}