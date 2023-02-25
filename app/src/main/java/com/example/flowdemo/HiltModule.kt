package com.example.flowdemo

import com.example.flowdemo.ui.main.*
import com.example.flowdemo.ui.main.configurator.ConfiguratorObserver
import com.example.flowdemo.ui.main.configurator.ConfiguratorObserverImp
import com.example.flowdemo.ui.main.data_sources.DanaeSportsDataSourceImp
import com.example.flowdemo.ui.main.data_sources.LegacySportsDataSourceImp
import com.example.flowdemo.ui.main.repositories.SportsRepository
import com.example.flowdemo.ui.main.repositories.SportsRepositoryImp
import com.example.flowdemo.ui.main.usecases.ObserveOnSportsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideObserveOnSportsUseCase(
        configuratorObserver: ConfiguratorObserver,
        sportsRepository: SportsRepository
    ): ObserveOnSportsUseCase = ObserveOnSportsUseCase(configuratorObserver, sportsRepository)

    @Provides
    fun provideConfiguratorObserver(): ConfiguratorObserver = ConfiguratorObserverImp()

    @Provides
    fun provideSportsRepository(
        danaeSportsDataSourceImp: DanaeSportsDataSourceImp,
        legacySportsDataSourceImp: LegacySportsDataSourceImp
    ): SportsRepository = SportsRepositoryImp(danaeSportsDataSourceImp, legacySportsDataSourceImp)

    @Provides
    fun danaeSportsDataSourceImp(): DanaeSportsDataSourceImp = DanaeSportsDataSourceImp()

    @Provides
    fun legacySportsDataSourceImp(): LegacySportsDataSourceImp = LegacySportsDataSourceImp()

}