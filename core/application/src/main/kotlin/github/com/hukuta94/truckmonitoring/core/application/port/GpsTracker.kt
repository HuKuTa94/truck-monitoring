package github.com.hukuta94.truckmonitoring.core.application.port

import github.com.hukuta94.truckmonitoring.core.domain.common.VIN

interface GpsTracker {
    fun start(vin: VIN)
    fun stop(vin: VIN)
}