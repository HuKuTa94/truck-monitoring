package github.com.hukuta94.truckmonitoring.core.domain.common

import github.com.hukuta94.truckmonitoring.core.domain.ValueObject

/**
 * Vehicle Identification Number
 */
@JvmInline
value class VIN private constructor(
    val value: String,
): ValueObject {
    companion object {
        private val VALID_VIN_PATTERN = Regex("^[A-HJ-NPR-Z0-9]{11,17}$")

        fun from(vin: String): VIN {
            val normalized = vin.trim().uppercase()
            require(VALID_VIN_PATTERN.matches(normalized)) {
                "VIN $vin is invalid. Valid VIN must be 11-17 chars, only A-Z and 0-9, without I, O, Q"
            }
            return VIN(normalized)
        }
    }
}
