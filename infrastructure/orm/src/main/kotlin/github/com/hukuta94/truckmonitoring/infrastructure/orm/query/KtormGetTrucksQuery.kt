package github.com.hukuta94.truckmonitoring.infrastructure.orm.query

import github.com.hukuta94.truckmonitoring.core.application.query.truck.GetTrucksQuery
import github.com.hukuta94.truckmonitoring.core.application.query.truck.GetTrucksResponse
import github.com.hukuta94.truckmonitoring.infrastructure.orm.model.TruckStatusesTable
import github.com.hukuta94.truckmonitoring.infrastructure.orm.model.TrucksTable
import org.ktorm.database.Database
import org.ktorm.dsl.*

class KtormGetTrucksQuery(
    private val database: Database,
): GetTrucksQuery {

    override fun execute(): List<GetTrucksResponse> {
        return database.from(TrucksTable)
            .leftJoin(TruckStatusesTable, on = TrucksTable.statusId eq TruckStatusesTable.id)
            .select(
                TrucksTable.vin,
                TruckStatusesTable.code,
                TrucksTable.latitude,
                TrucksTable.longitude,
            )
            .map { row ->
                GetTrucksResponse(
                    vin = row[TrucksTable.vin]!!,
                    status = row[TruckStatusesTable.code]!!,
                    latitude = row[TrucksTable.latitude]!!,
                    longitude = row[TrucksTable.longitude]!!,
                )
            }
    }
}