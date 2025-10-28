package github.com.hukuta94.truckmonitoring.configuration.core.application.port

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    GpsTrackerEmulatorConfiguration::class,
)
open class PortConfiguration {

}