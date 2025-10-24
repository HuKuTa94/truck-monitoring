package github.com.hukuta94.truckmonitoring.core.application.usecase.truck

import github.com.hukuta94.truckmonitoring.core.application.usecase.UseCase

interface CreateTruckUseCase : UseCase {
    fun execute(command: CreateTruckCommand)
}

data class CreateTruckCommand (
    val vin: String,
)
