package github.com.hukuta94.truckmonitoring.configuration.api.kafka

import github.com.hukuta94.truckmonitoring.api.kafka.KafkaConsumer
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.ChangeTruckCoordinateUseCase
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@EnableKafka
@Configuration
open class KafkaConsumerConfiguration {

    @Bean
    open fun truckMonitoringKafkaConsumerFactory(): ConsumerFactory<String, String> {
        val properties = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
            ConsumerConfig.GROUP_ID_CONFIG to "truck-monitoring-group",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
        )
        return DefaultKafkaConsumerFactory(properties)
    }

    @Bean
    open fun truckMonitoringKafkaListenerContainerFactory(
        truckMonitoringKafkaConsumerFactory: ConsumerFactory<String, String>,
    ): ConcurrentKafkaListenerContainerFactory<String, String> {
        return ConcurrentKafkaListenerContainerFactory<String, String>().apply {
            consumerFactory = truckMonitoringKafkaConsumerFactory
        }
    }

    @Bean
    open fun truckMonitoringKafkaConsumer(
        changeTruckCoordinateUseCase: ChangeTruckCoordinateUseCase,
    ) = KafkaConsumer(
        changeTruckCoordinateUseCase = changeTruckCoordinateUseCase,
    )
}