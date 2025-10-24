package github.com.hukuta94.truckmonitoring.core.application.port.repository.domain

import github.com.hukuta94.truckmonitoring.core.domain.aggregate.Aggregate
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN

interface AggregateRepositoryPort<AGGREGATE : Aggregate<*>> {

    fun create(aggregate: AGGREGATE)

    fun findByVin(vin: VIN): AGGREGATE? //TODO replace return type to Either

    fun update(aggregate: AGGREGATE)

    fun update(aggregates: Collection<AGGREGATE>)
}