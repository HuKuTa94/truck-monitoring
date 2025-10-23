package github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck

import github.com.hukuta94.truckmonitoring.core.domain.common.randomGeoCoordinate
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class TruckTest : StringSpec({

    val zone = ZoneOffset.UTC
    val baseTime = Instant.parse("2025-10-10T12:00:00Z")

    fun clockAt(minutes: Long) = Clock.fixed(baseTime.plusSeconds(minutes * 60), zone)
    val now = clockAt(0)

    "must change coordinate" {
        val oldCoordinate = randomGeoCoordinate()
        val newCoordinate = randomGeoCoordinate()
        val truck = newTruck(coordinate = oldCoordinate)

        truck.changeCoordinate(newCoordinate, clockAt(2))

        truck.coordinate shouldBe newCoordinate
    }

    "must set status MOVING if coordinate was changed less than 1 minute ago" {
        val after30s = Clock.fixed(baseTime.plusSeconds(30), zone)
        val truck = newTruck(
            clock = now,
            status = TruckStatus.PARKED,
        )

        truck.changeCoordinate(randomGeoCoordinate(), now)
        val actual = truck.getStatus(after30s)

        actual shouldBe TruckStatus.MOVING
    }

    "must set status PARKED if coordinate unchanged for more than 1 minute" {
        val after2m = clockAt(2)
        val coordinate = randomGeoCoordinate()
        val truck = newTruck(
            clock = now,
            coordinate = coordinate
        )

        truck.changeCoordinate(coordinate, clockAt(1))
        val actual = truck.getStatus(after2m)

        actual shouldBe TruckStatus.PARKED
    }

    "must set status NO_SIGNAL if no coordinates received for more than 3 minutes" {
        val after5m = clockAt(5)
        val truck = newTruck(
            clock = now,
        )

        val actual = truck.getStatus(after5m)

        actual shouldBe TruckStatus.NO_SIGNAL
    }
})