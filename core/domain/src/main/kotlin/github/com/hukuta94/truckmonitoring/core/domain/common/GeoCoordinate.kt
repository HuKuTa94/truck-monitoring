package github.com.hukuta94.truckmonitoring.core.domain.common

import java.math.BigDecimal

data class GeoCoordinate private constructor(
    val latitude: BigDecimal,
    val longitude: BigDecimal,
) {
    fun latitudeToString() = latitude.toPlainString()
    fun longitudeToString() = longitude.toPlainString()

    companion object {
        private val ZERO = BigDecimal("0.00000")
        private val LATITUDE_RANGE = BigDecimal("-90.0000") .. BigDecimal("90.0000")
        private val LONGITUDE_RANGE = BigDecimal("-180.0000") .. BigDecimal("180.0000")

        fun from(
            latitude: BigDecimal,
            longitude: BigDecimal,
        ): GeoCoordinate {
            require(latitude in LATITUDE_RANGE) {
                "Invalid latitude value: $latitude. Valid latitude range: $LATITUDE_RANGE"
            }
            require(longitude in LONGITUDE_RANGE) {
                "Invalid longitude value: $longitude. Valid longitude range: $LONGITUDE_RANGE"
            }
            return GeoCoordinate(latitude, longitude)
        }

        fun zero() = GeoCoordinate(ZERO, ZERO)
    }
}
