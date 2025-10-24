package github.com.hukuta94.truckmonitoring.configuration.infrastructure

import github.com.hukuta94.truckmonitoring.configuration.infrastructure.orm.OrmRepositoryConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    OrmRepositoryConfiguration::class
)
open class InfrastructureConfiguration