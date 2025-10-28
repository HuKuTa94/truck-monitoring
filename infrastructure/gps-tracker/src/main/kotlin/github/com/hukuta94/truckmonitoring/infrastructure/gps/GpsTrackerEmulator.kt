package github.com.hukuta94.truckmonitoring.infrastructure.gps

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import github.com.hukuta94.truckmonitoring.core.application.port.GpsTrackerPort
import github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck.TruckStatus
import github.com.hukuta94.truckmonitoring.core.domain.common.GeoCoordinate
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import java.time.LocalDateTime
import org.springframework.scheduling.annotation.Scheduled
import java.time.Instant
import java.time.ZoneOffset

class GpsTrackerEmulator(
    private val coordinates: List<GpsEmulatorCoordinate>,
    private val kafkaTemplate: KafkaTemplate<String, String>,
) : GpsTrackerPort {

    private var offset = 0
    private val trackableVins = mutableSetOf<VIN>()

    override fun start(vin: VIN) {
        trackableVins.add(vin)
    }

    override fun stop(vin: VIN) {
        trackableVins.remove(vin)
    }

    @Scheduled(fixedDelay = 3000)
    fun emulate() {
        val coordinate = coordinates[offset]

        trackableVins.forEach { vin ->
            val message = JSON.writeValueAsString(
                TruckPositionMessage(
                    vin = vin.value,
                    latitude = coordinate.coordinate.latitude.toDouble(),
                    longitude = coordinate.coordinate.longitude.toDouble(),
                    timestamp = coordinate.timestamp.toInstant(ZoneOffset.UTC),
                    status = coordinate.status.name
                )
            )
            val record = ProducerRecord(
                KAFKA_TOPIC,
                vin.value,
                message,
            )
            kafkaTemplate.send(record)
        }

        if (offset == coordinates.size - 1) {
            offset = 0
        } else {
            offset++
        }
    }

    companion object {
        private const val KAFKA_TOPIC = "truck-positions"

        private val JSON = jacksonObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }
}

data class GpsEmulatorCoordinate(
    val coordinate: GeoCoordinate,
    val timestamp: LocalDateTime,
    val status: TruckStatus,
)

data class TruckPositionMessage(
    val vin: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Instant,
    val status: String,
)
