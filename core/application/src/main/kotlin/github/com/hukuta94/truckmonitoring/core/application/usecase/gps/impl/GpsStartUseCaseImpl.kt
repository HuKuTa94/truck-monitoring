package github.com.hukuta94.truckmonitoring.core.application.usecase.gps.impl

import github.com.hukuta94.truckmonitoring.core.application.port.GpsTrackerPort
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStartCommand
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStartUseCase

class GpsStartUseCaseImpl(
    private val gpsTrackerPort: GpsTrackerPort,
) : GpsStartUseCase {
    override fun execute(command: GpsStartCommand) {
        gpsTrackerPort.start(command.vin)
    }
}