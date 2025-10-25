package github.com.hukuta94.truckmonitoring.api.kafka

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.ChangeTruckCoordinateCommand
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.ChangeTruckCoordinateUseCase
import github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck.TruckStatus
import github.com.hukuta94.truckmonitoring.core.domain.common.GeoCoordinate
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class KafkaConsumer(
    private val changeTruckCoordinateUseCase: ChangeTruckCoordinateUseCase,
) {
    @KafkaListener(
        groupId = "truck-positions-group",
        topics = [ "truck-positions" ],
    )
    fun truckPositionsTopic(record: ConsumerRecord<String, String>) {
        val message = JSON.readValue(record.value(), TruckPositionMessage::class.java)

        val command = ChangeTruckCoordinateCommand(
            vin = VIN.from(message.vin),
            coordinate = GeoCoordinate.from(
                latitude = BigDecimal.valueOf(message.latitude),
                longitude = BigDecimal.valueOf(message.longitude),
            ),
            timestamp = LocalDateTime.ofInstant(message.timestamp, ZoneOffset.UTC),
            status = TruckStatus.valueOf(message.status),
        )

        changeTruckCoordinateUseCase.execute(command)
    }

    companion object {
        private val JSON = jacksonObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }
}

data class TruckPositionMessage(
    val vin: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Instant,
    val status: String,
)