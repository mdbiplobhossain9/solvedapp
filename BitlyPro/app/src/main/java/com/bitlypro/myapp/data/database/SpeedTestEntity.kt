package com.bitlypro.myapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "speed_test_results")
data class SpeedTestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val downloadSpeed: Double, // Mbps
    val uploadSpeed: Double,   // Mbps
    val ping: Long,           // ms
    val testDate: Date,
    val serverLocation: String = "Unknown",
    val connectionType: String = "Unknown"
)