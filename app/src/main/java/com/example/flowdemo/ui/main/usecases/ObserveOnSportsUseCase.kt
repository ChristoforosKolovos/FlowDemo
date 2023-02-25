package com.example.flowdemo.ui.main.usecases

import com.example.flowdemo.ui.main.configurator.ConfiguratorObserver
import com.example.flowdemo.ui.main.repositories.SportsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ObserveOnSportsUseCase(
    private val configuratorObserver: ConfiguratorObserver,
    private val sportsRepository: SportsRepository,
) {

    private var sportsJob: Job = Job()
    private var configuratorJob: Job = Job()
    private val _sportsFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val sportsFlow: StateFlow<Int> = _sportsFlow

    fun subscribe(scope: CoroutineScope) {
        observeOnConfigurator(scope)
    }

    fun unsubscribe() {
        sportsJob.cancel()
        configuratorJob.cancel()
    }

    private fun observeOnConfigurator(scope: CoroutineScope) {
        configuratorJob = scope.launch {
            configuratorObserver.observe().collect { config ->
                println("DBG: USECASE COLLECTED NEW CONFIG = ${if (config) "LEGACY" else "DANAE"}")
                sportsJob.cancel()
                observeOnSports(scope, config)
            }
        }
    }

    private suspend fun observeOnSports(scope: CoroutineScope, legacy: Boolean) {
        sportsJob = scope.launch {
            sportsRepository.getSports(legacy).collect { sports ->
                println("DBG: USECASE COLLECTED NEW SPORTS (${if (legacy) "LEGACY" else "DANAE"}) = $sports")
                _sportsFlow.value = sports
            }
        }
    }


}