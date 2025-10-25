package github.com.hukuta94.truckmonitoring.core.application.usecase.truck

import github.com.hukuta94.truckmonitoring.core.application.usecase.UseCase
import github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck.TruckStatus
import github.com.hukuta94.truckmonitoring.core.domain.common.GeoCoordinate
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN
import java.time.LocalDateTime

interface ChangeTruckCoordinateUseCase : UseCase {
    fun execute(command: ChangeTruckCoordinateCommand)
}

data class ChangeTruckCoordinateCommand (
    val vin: VIN,
    val coordinate: GeoCoordinate,
    val timestamp: LocalDateTime,
    val status: TruckStatus,
)
