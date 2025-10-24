package github.com.hukuta94.truckmonitoring.core.application.usecase.truck.impl

import github.com.hukuta94.truckmonitoring.core.application.port.repository.domain.TruckRepositoryPort
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.CreateTruckCommand
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.CreateTruckUseCase
import github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck.Truck
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN
import java.time.Clock

class CreateTruckUseCaseImpl(
    private val clock: Clock,
    private val truckRepositoryPort: TruckRepositoryPort,
) : CreateTruckUseCase {

    override fun execute(command: CreateTruckCommand)  {
        truckRepositoryPort.create(
            Truck.create(
                vin = VIN.from(command.vin),
                clock = clock,
            )
        )
    }
}