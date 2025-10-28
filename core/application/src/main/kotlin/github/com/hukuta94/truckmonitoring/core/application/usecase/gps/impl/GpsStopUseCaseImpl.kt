package github.com.hukuta94.truckmonitoring.core.application.usecase.gps.impl

import github.com.hukuta94.truckmonitoring.core.application.port.GpsTrackerPort
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStopCommand
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStopUseCase

class GpsStopUseCaseImpl(
    private val gpsTrackerPort: GpsTrackerPort,
) : GpsStopUseCase {
    override fun execute(command: GpsStopCommand) {
        gpsTrackerPort.stop(command.vin)
    }
}