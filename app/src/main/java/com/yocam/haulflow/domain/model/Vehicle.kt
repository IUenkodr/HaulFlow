package com.yocam.haulflow.domain.model

import com.google.android.gms.maps.model.LatLng

enum class VehicleStatus {
    IN_TRANSIT, LOADING, UNLOADING, MAINTENANCE, IDLE
}

data class Vehicle(
    val id: String,
    val truckNumber: String,
    val driverName: String,
    val currentLocation: LatLng,
    val status: VehicleStatus,
    val destination: LatLng?,
    val lastUpdated: Long = System.currentTimeMillis()
)
