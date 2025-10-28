package github.com.hukuta94.truckmonitoring.configuration.core.application.usecase

import github.com.hukuta94.truckmonitoring.core.application.port.GpsTrackerPort
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStartUseCase
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStopUseCase
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.impl.GpsStopUseCaseImpl
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.impl.GpsStartUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class GpsUseCaseConfiguration {

    @Bean
    open fun gpsStartUseCase(gpsTrackerPort: GpsTrackerPort): GpsStartUseCase = GpsStartUseCaseImpl(gpsTrackerPort)

    @Bean
    open fun gpsStopUseCase(gpsTrackerPort: GpsTrackerPort): GpsStopUseCase = GpsStopUseCaseImpl(gpsTrackerPort)
}