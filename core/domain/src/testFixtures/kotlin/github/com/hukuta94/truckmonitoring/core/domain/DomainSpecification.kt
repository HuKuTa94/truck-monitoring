package github.com.hukuta94.truckmonitoring.core.domain

import java.math.BigDecimal

internal object GeoCoordinateSpecification {
    val LATITUDE_MIN = BigDecimal("-90.0000")
    val LATITUDE_MAX = BigDecimal("90.0000")
    val LATITUDE_RANGE = LATITUDE_MIN .. LATITUDE_MAX

    val LONGITUDE_MIN = BigDecimal("-180.0000")
    val LONGITUDE_MAX = BigDecimal("180.0000")
    val LONGITUDE_RANGE = LONGITUDE_MIN .. LONGITUDE_MAX
}