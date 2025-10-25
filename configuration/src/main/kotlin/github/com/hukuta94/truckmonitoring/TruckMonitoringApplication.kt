package github.com.hukuta94.truckmonitoring

import github.com.hukuta94.truckmonitoring.configuration.TruckMonitoringApplicationConfiguration
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    runApplication<TruckMonitoringApplicationConfiguration>(*args)
}