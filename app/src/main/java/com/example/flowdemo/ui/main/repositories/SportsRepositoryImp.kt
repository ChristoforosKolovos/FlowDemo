package com.example.flowdemo.ui.main.repositories

import com.example.flowdemo.ui.main.data_sources.SportsDataSource
import kotlinx.coroutines.flow.Flow

class SportsRepositoryImp(
    private val danaeDataSource: SportsDataSource,
    private val legacyDataSource: SportsDataSource
) : SportsRepository {

    override suspend fun getSports(legacy: Boolean): Flow<Int> {
        val dataSource = if (legacy) legacyDataSource else danaeDataSource
        return dataSource.getSports()
    }
}