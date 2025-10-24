package github.com.hukuta94.truckmonitoring.core.application.query.truck

import github.com.hukuta94.truckmonitoring.core.application.query.Query

interface GetTrucksQuery : Query {
    fun execute(): List<GetTrucksResponse>
}

data class GetTrucksResponse(
    val vin: String,
    val status: String,
    val latitude: String,
    val longitude: String,
)