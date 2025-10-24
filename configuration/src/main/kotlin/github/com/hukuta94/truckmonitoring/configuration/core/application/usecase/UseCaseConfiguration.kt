package github.com.hukuta94.truckmonitoring.configuration.core.application.usecase

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    TruckUseCaseConfiguration::class,
    MockUseCaseConfiguration::class,
)
open class UseCaseConfiguration {

}