package github.com.hukuta94.truckmonitoring.infrastructure.orm.repository

import github.com.hukuta94.truckmonitoring.core.application.port.repository.domain.TruckRepositoryPort
import github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck.Truck
import github.com.hukuta94.truckmonitoring.core.domain.aggregate.truck.TruckStatus
import github.com.hukuta94.truckmonitoring.core.domain.common.GeoCoordinate
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN
import github.com.hukuta94.truckmonitoring.infrastructure.orm.model.TrucksTable
import org.ktorm.database.Database
import org.ktorm.dsl.batchUpdate
import org.ktorm.dsl.eq
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.limit
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import org.ktorm.dsl.update
import org.ktorm.dsl.where
import java.math.BigDecimal
import java.time.Clock

class KtormTruckRepository(
    private val database: Database,
    private val clock: Clock,
) : TruckRepositoryPort {
    override fun create(aggregate: Truck) {
        database.insert(TrucksTable) {
            set(it.id, aggregate.id)
            set(it.vin, aggregate.vin.value)
            set(it.statusId, aggregate.getStatus(clock).id)
            set(it.latitude, aggregate.coordinate.latitudeToString())
            set(it.longitude, aggregate.coordinate.longitudeToString())
            set(it.coordinateChangedAt, aggregate.coordinateChangedAt)
            set(it.gpsSignalReceivedAt, aggregate.gpsSignalReceivedAt)
        }
    }

    override fun findByVin(vin: VIN): Truck? {
        return database
            .from(TrucksTable)
            .select()
            .where { TrucksTable.vin eq vin.value }
            .limit(1)
            .map { row ->
                Truck(
                    id = row[TrucksTable.id]!!,
                    vin = vin,
                    coordinate = GeoCoordinate.from(
                        latitude = BigDecimal(row[TrucksTable.latitude]!!),
                        longitude = BigDecimal(row[TrucksTable.longitude]!!)
                    ),
                    status = TruckStatus.from(row[TrucksTable.statusId]!!),
                    coordinateChangedAt = row[TrucksTable.coordinateChangedAt]!!,
                    gpsSignalReceivedAt = row[TrucksTable.gpsSignalReceivedAt]!!,
                )
            }
            .firstOrNull()
    }

    override fun update(aggregate: Truck) {
        database.update(TrucksTable) {
            set(it.statusId, aggregate.getStatus(clock).id)
            set(it.latitude, aggregate.coordinate.latitudeToString())
            set(it.longitude, aggregate.coordinate.longitudeToString())
            set(it.coordinateChangedAt, aggregate.coordinateChangedAt)
            set(it.gpsSignalReceivedAt, aggregate.gpsSignalReceivedAt)
            where { it.id eq aggregate.id }
        }
    }

    override fun update(aggregates: Collection<Truck>) {
        database.batchUpdate(TrucksTable) {
            aggregates.forEach { aggregate ->
                item {
                    set(it.statusId, aggregate.getStatus(clock).id)
                    set(it.latitude, aggregate.coordinate.latitudeToString())
                    set(it.longitude, aggregate.coordinate.longitudeToString())
                    set(it.coordinateChangedAt, aggregate.coordinateChangedAt)
                    set(it.gpsSignalReceivedAt, aggregate.gpsSignalReceivedAt)
                    where { it.id eq aggregate.id }
                }
            }
        }
    }
}