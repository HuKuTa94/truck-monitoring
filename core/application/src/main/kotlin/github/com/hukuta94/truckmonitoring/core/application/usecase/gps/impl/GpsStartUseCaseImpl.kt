package github.com.hukuta94.truckmonitoring.core.application.usecase.gps.impl

import github.com.hukuta94.truckmonitoring.core.application.port.GpsTracker
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStartCommand
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStartUseCase

class GpsStartUseCaseImpl(
    private val gpsTracker: GpsTracker,
) : GpsStartUseCase {
    override fun execute(command: GpsStartCommand) {
        gpsTracker.start(command.vin)
    }
}