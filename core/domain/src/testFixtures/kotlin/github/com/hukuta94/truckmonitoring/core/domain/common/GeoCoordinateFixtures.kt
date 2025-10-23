package github.com.hukuta94.truckmonitoring.core.domain.common

import github.com.hukuta94.truckmonitoring.core.domain.GeoCoordinateSpecification.LATITUDE_RANGE
import github.com.hukuta94.truckmonitoring.core.domain.GeoCoordinateSpecification.LONGITUDE_RANGE
import java.math.BigDecimal
import kotlin.random.Random

fun randomGeoCoordinate() = GeoCoordinate.from(
    latitude = LATITUDE_RANGE.random(),
    longitude = LONGITUDE_RANGE.random(),
)

private fun ClosedRange<BigDecimal>.random(): BigDecimal {
    val from = this.start.toDouble()
    val to = this.endInclusive.toDouble()
    val randomDouble = Random.nextDouble(from, to)
    return BigDecimal(randomDouble)
}