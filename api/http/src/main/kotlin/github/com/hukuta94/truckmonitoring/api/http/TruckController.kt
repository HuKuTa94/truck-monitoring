package github.com.hukuta94.truckmonitoring.api.http

import github.com.hukuta94.truckmonitoring.core.application.query.truck.GetTrucksQuery
import github.com.hukuta94.truckmonitoring.core.application.query.truck.GetTrucksResponse
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.CreateTruckCommand
import github.com.hukuta94.truckmonitoring.core.application.usecase.truck.CreateTruckUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TruckController(
    private val createTruckUseCase: CreateTruckUseCase,
    private val getTrucksQuery: GetTrucksQuery,
) {

    @GetMapping("trucks/")
    fun getTrucks(): ResponseEntity<List<GetTrucksResponse>> {
        val trucks = getTrucksQuery.execute()
        return ResponseEntity.ok(trucks)
    }

    @PostMapping("trucks/{vin}")
    fun postTrucks(@PathVariable vin: String): ResponseEntity<Void> {
        val command = CreateTruckCommand(vin)
        createTruckUseCase.execute(command)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}