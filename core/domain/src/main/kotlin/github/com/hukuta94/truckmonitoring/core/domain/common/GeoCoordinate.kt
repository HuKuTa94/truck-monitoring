package github.com.hukuta94.truckmonitoring.core.domain.common

import java.math.BigDecimal

data class GeoCoordinate private constructor(
    val latitude: BigDecimal,
    val longitude: BigDecimal,
) {
    companion object {
        private val LATITUDE_RANGE = BigDecimal("-90") .. BigDecimal("90")
        private val LONGITUDE_RANGE = BigDecimal("-180") .. BigDecimal("180")

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
    }
}
