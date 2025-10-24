package github.com.hukuta94.truckmonitoring

import github.com.hukuta94.truckmonitoring.configuration.DeliveryApplicationConfiguration
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    runApplication<DeliveryApplicationConfiguration>(*args)
}