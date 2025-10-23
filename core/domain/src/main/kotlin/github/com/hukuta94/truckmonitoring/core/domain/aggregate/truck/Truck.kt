package github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck

import github.com.hukuta94.truckmonitoring.core.domain.aggregate.Aggregate
import github.com.hukuta94.truckmonitoring.core.domain.common.GeoCoordinate
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN
import java.time.LocalDateTime
import java.time.Clock
import java.time.Duration
import java.util.UUID

class Truck(
    override val id: UUID,
    val vin: VIN,
    private var status: TruckStatus,
    coordinate: GeoCoordinate,
    private var coordinateChangedAt: LocalDateTime,
    private var gpsSignalReceivedAt: LocalDateTime,
) : Aggregate<UUID>() {

    var coordinate: GeoCoordinate = coordinate
        private set

    fun getStatus(clock: Clock): TruckStatus {
        val now = LocalDateTime.now(clock)

        val minutesSinceLastGpsSignal = Duration.between(gpsSignalReceivedAt, now).toMinutes()
        if (minutesSinceLastGpsSignal > 3) {
            return TruckStatus.NO_SIGNAL
        }

        val minutesSinceLastChange = Duration.between(coordinateChangedAt, now).toMinutes()
        return when {
            minutesSinceLastChange >= 1 ->TruckStatus.PARKED
            else -> TruckStatus.MOVING
        }
    }

    fun changeCoordinate(newCoordinate: GeoCoordinate, clock: Clock) {
        val now = LocalDateTime.now(clock)
        gpsSignalReceivedAt = now

        if (coordinate == newCoordinate) {
            status = TruckStatus.PARKED
        } else {
            coordinate = newCoordinate
            coordinateChangedAt = now
            status = TruckStatus.MOVING
        }
    }

    companion object {
        fun create(
            id: UUID = UUID.randomUUID(),
            vin: VIN,
            status: TruckStatus = TruckStatus.PARKED,
            coordinate: GeoCoordinate = GeoCoordinate.zero(),
            clock: Clock = Clock.systemDefaultZone(),
        ): Truck {
            val now = LocalDateTime.now(clock)
            return Truck(
                id = id,
                vin = vin,
                status = status,
                coordinate = coordinate,
                coordinateChangedAt = now,
                gpsSignalReceivedAt = now,
            )
        }
    }
}