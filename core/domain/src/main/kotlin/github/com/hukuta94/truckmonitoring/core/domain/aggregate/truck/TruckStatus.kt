package github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck

enum class TruckStatus(val id: Int) {
    MOVING(1),
    PARKED(2),
    NO_SIGNAL(3),
    ;

    companion object {
        fun from(id: Int) = entries
            .firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("Enum value is not found by id: $id")
    }
}