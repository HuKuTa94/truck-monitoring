package github.com.hukuta94.truckmonitoring.configuration.core.application.usecase

import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.CreateTruckUseCase
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.impl.CreateTruckUseCaseImpl
import github.com.hukuta94.truckmonitoring.core.application.port.repository.domain.TruckRepositoryPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
open class TruckUseCaseConfiguration {

    @Bean
    open fun createTruckUseCase(
        truckRepositoryPort: TruckRepositoryPort,
        clock: Clock,
    ): CreateTruckUseCase = CreateTruckUseCaseImpl(
        truckRepositoryPort = truckRepositoryPort,
        clock = clock,
    )
}