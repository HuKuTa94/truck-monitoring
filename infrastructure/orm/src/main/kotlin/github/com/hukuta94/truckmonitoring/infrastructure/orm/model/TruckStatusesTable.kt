package github.com.hukuta94.truckmonitoring.infrastructure.orm.model

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object TruckStatusesTable : Table<Nothing>("truck_statuses") {
    val id = int("id").primaryKey()
    val code = varchar("code")
}