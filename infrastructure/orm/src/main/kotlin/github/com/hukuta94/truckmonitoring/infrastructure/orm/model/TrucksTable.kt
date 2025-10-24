package github.com.hukuta94.truckmonitoring.infrastructure.orm.model

import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.int
import org.ktorm.schema.uuid
import org.ktorm.schema.varchar

object TrucksTable : Table<Nothing>("trucks") {
    val id = uuid("id").primaryKey()
    val vin = varchar("vin")
    val statusId = int("status_id")
    val latitude = varchar("latitude")
    val longitude = varchar("longitude")
    val coordinateChangedAt = datetime("coordinate_changed_at")
    val gpsSignalReceivedAt = datetime("gps_signal_received_at")
}