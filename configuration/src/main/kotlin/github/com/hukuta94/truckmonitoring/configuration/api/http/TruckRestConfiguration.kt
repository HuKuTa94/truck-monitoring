package github.com.hukuta94.truckmonitoring.configuration.api.http

import github.com.hukuta94.truckmonitoring.api.http.TruckController
import github.com.hukuta94.truckmonitoring.core.application.query.truck.GetTrucksQuery
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.CreateTruckUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class TruckRestConfiguration {
    @Bean
    open fun truckController(
        createTruckUseCase: CreateTruckUseCase,
        getTrucksQuery: GetTrucksQuery,
    ) = TruckController(
        createTruckUseCase,
        getTrucksQuery,
    )
}