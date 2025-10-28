package github.com.hukuta94.truckmonitoring.configuration.core.application.usecase

import github.com.hukuta94.truckmonitoring.core.application.port.GpsTracker
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStartUseCase
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStopUseCase
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.impl.GpsStopUseCaseImpl
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.impl.GpsStartUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class GpsUseCaseConfiguration {

    @Bean
    open fun gpsStartUseCase(gpsTracker: GpsTracker): GpsStartUseCase = GpsStartUseCaseImpl(gpsTracker)

    @Bean
    open fun gpsStopUseCase(gpsTracker: GpsTracker): GpsStopUseCase = GpsStopUseCaseImpl(gpsTracker)
}