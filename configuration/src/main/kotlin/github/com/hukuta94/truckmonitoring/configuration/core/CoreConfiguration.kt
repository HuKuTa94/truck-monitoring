package github.com.hukuta94.truckmonitoring.configuration.core

import github.com.hukuta94.truckmonitoring.configuration.core.application.port.PortConfiguration
import github.com.hukuta94.truckmonitoring.configuration.core.application.usecase.UseCaseConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    UseCaseConfiguration::class,
    PortConfiguration::class,
)
open class CoreConfiguration