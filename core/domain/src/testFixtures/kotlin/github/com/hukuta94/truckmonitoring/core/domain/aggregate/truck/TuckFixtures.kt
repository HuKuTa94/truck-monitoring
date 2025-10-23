package github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck

import github.com.hukuta94.truckmonitoring.core.domain.common.GeoCoordinate
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN
import github.com.hukuta94.truckmonitoring.core.domain.common.newVIN
import github.com.hukuta94.truckmonitoring.core.domain.common.randomGeoCoordinate
import java.time.Clock

fun newTruck(
    vin: VIN = newVIN(),
    status: TruckStatus = TruckStatus.PARKED,
    coordinate: GeoCoordinate = randomGeoCoordinate(),
    clock: Clock = Clock.systemDefaultZone()
) = Truck.create(
    vin = vin,
    status = status,
    coordinate = coordinate,
    clock = clock,
)