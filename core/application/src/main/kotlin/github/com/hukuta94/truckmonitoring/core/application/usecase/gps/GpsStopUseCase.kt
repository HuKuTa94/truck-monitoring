package github.com.hukuta94.truckmonitoring.core.application.usecase.gps

import github.com.hukuta94.truckmonitoring.core.application.usecase.UseCase
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN

interface GpsStopUseCase : UseCase {
    fun execute(command: GpsStopCommand)
}

data class GpsStopCommand(
    val vin: VIN,
)