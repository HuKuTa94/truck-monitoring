package github.com.hukuta94.truckmonitoring.configuration.infrastructure.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@EnableKafka
@Configuration
open class KafkaProducerConfiguration {

    @Bean
    open fun truckMonitoringKafkaProducerFactory(): ProducerFactory<String, String> {
        val properties = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        )
        return DefaultKafkaProducerFactory(properties)
    }

    @Bean
    open fun truckMonitoringKafkaTemplate(
        truckMonitoringKafkaProducerFactory: ProducerFactory<String, String>
    ) = KafkaTemplate(truckMonitoringKafkaProducerFactory)
}