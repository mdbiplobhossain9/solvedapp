package com.bitlypro.myapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "speed_test_results")
data class SpeedTestEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val downloadSpeed: Float,
    val uploadSpeed: Float,
    val ping: Float,
    val timestamp: Long
)