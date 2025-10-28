package github.com.hukuta94.truckmonitoring.configuration.api

import github.com.hukuta94.truckmonitoring.configuration.api.http.MockRestConfiguration
import github.com.hukuta94.truckmonitoring.configuration.api.http.RestControllerConfiguration
import github.com.hukuta94.truckmonitoring.configuration.api.kafka.KafkaConsumerConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    RestControllerConfiguration::class,
    KafkaConsumerConfiguration::class,
    MockRestConfiguration::class,
)
open class ApiConfiguration