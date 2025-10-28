package github.com.hukuta94.truckmonitoring.configuration.api.http

import github.com.hukuta94.truckmonitoring.api.http.MockController
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStartUseCase
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStopUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MockRestConfiguration {
    @Bean
    open fun mockController(
        gpsStartUseCase: GpsStartUseCase,
        gpsStopUseCase: GpsStopUseCase,
    ) = MockController(
        gpsStartUseCase,
        gpsStopUseCase,
    )
}