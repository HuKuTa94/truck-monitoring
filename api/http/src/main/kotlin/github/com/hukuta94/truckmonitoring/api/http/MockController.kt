package github.com.hukuta94.truckmonitoring.api.http

import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStartCommand
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStartUseCase
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStopCommand
import github.com.hukuta94.truckmonitoring.core.application.usecase.gps.GpsStopUseCase
import github.com.hukuta94.truckmonitoring.core.domain.common.VIN
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/mock")
class MockController(
    val gpsStartUseCase: GpsStartUseCase,
    val gpsStopUseCase: GpsStopUseCase,
) {

    @PostMapping("start/{vin}")
    fun mockStart(@PathVariable vin: String): ResponseEntity<Void> {
        val command = GpsStartCommand(
            vin = VIN.from(vin)
        )
        gpsStartUseCase.execute(command)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PostMapping("stop/{vin}")
    fun mockStop(@PathVariable vin: String): ResponseEntity<Void> {
        val command = GpsStopCommand(
            vin = VIN.from(vin)
        )
        gpsStopUseCase.execute(command)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}