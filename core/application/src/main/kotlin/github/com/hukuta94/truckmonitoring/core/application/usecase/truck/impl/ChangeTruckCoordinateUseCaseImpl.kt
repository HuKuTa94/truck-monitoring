package github.com.hukuta94.truckmonitoring.core.application.usecase.truck.impl

import github.com.hukuta94.truckmonitoring.core.application.port.repository.domain.TruckRepositoryPort
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.ChangeTruckCoordinateCommand
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.ChangeTruckCoordinateUseCase
import java.time.Clock

class ChangeTruckCoordinateUseCaseImpl(
    private val truckRepositoryPort: TruckRepositoryPort,
    private val clock: Clock,
) : ChangeTruckCoordinateUseCase {

    override fun execute(command: ChangeTruckCoordinateCommand) {
        val truck = truckRepositoryPort.findByVin(command.vin) //TODO возвращать Either, чтобы убрать nullable
        if (truck == null) return

        truck.changeCoordinate(command.coordinate, clock)

        truckRepositoryPort.update(truck)
    }
}