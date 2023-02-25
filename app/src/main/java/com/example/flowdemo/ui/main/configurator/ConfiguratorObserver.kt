package com.example.flowdemo.ui.main.configurator

import kotlinx.coroutines.flow.Flow

interface ConfiguratorObserver {

    suspend fun observe(): Flow<Boolean>
}