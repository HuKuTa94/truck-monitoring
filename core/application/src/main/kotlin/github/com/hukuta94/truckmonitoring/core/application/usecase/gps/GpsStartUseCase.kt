package github.com.hukuta94.truckmonitoring.core.application.usecase.gps

import github.com.hukuta94.truckmonitoring.core.application.usecase.UseCase
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN

interface GpsStartUseCase : UseCase {
    fun execute(command: GpsStartCommand)
}

data class GpsStartCommand(
    val vin: VIN,
)