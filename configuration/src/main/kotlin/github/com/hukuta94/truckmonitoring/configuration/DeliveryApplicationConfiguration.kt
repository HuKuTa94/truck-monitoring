package github.com.hukuta94.truckmonitoring.configuration

import github.com.hukuta94.truckmonitoring.configuration.api.ApiConfiguration
import github.com.hukuta94.truckmonitoring.configuration.core.CoreConfiguration
import github.com.hukuta94.truckmonitoring.configuration.infrastructure.InfrastructureConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import java.time.Clock

@Configuration
@EnableAutoConfiguration
@Import(
    ApiConfiguration::class,
    CoreConfiguration::class,
    InfrastructureConfiguration::class,
)
open class DeliveryApplicationConfiguration {
    @Bean
    open fun applicationClock(): Clock = Clock.systemDefaultZone()
}