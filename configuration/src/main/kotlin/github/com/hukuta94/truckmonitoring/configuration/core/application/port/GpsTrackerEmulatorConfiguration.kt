package github.com.hukuta94.truckmonitoring.configuration.core.application.port

import github.com.hukuta94.truckmonitoring.core.application.port.GpsTracker
import github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck.TruckStatus
import github.com.hukuta94.truckmonitoring.core.domain.common.GeoCoordinate
import github.com.hukuta94.truckmonitoring.infrastructure.gps.GpsEmulatorCoordinate
import github.com.hukuta94.truckmonitoring.infrastructure.gps.GpsTrackerEmulator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.time.LocalDateTime

@EnableScheduling
@Configuration
open class GpsTrackerEmulatorConfiguration {

    @Bean
    open fun gpsEmulatorCoordinates(): List<GpsEmulatorCoordinate> {
        val resource = javaClass.classLoader.getResourceAsStream("coordinates.csv")
            ?: throw IllegalStateException("coordinates.csv not found in resources")

        BufferedReader(InputStreamReader(resource)).use { reader ->
            return reader
                .lineSequence()
                .drop(1) // skip header
                .filter { it.isNotBlank() }
                .map { line ->
                    val parts = line.split(",")
                    GpsEmulatorCoordinate(
                        coordinate = GeoCoordinate.from(
                            latitude = BigDecimal(parts[0].trim()),
                            longitude = BigDecimal(parts[1].trim()),
                        ),
                        timestamp = LocalDateTime.parse(parts[2].trim()),
                        status = TruckStatus.valueOf(parts[3].trim()),
                    )
                }
                .toList()
        }
    }

    @Bean
    open fun gpsTrackerEmulator(
        gpsEmulatorCoordinates: List<GpsEmulatorCoordinate>,
        kafkaTemplate: KafkaTemplate<String, String>,
    ): GpsTracker = GpsTrackerEmulator(
        gpsEmulatorCoordinates,
        kafkaTemplate,
    )
}
