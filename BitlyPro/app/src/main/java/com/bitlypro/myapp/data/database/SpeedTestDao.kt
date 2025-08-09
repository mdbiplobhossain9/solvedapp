package com.bitlypro.myapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bitlypro.myapp.data.database.SpeedTestEntity

@Dao
interface SpeedTestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeedTestResult(speedTest: SpeedTestEntity)

    @Query("SELECT * FROM speed_test ORDER BY timestamp DESC")
    suspend fun getAllSpeedTestResults(): List<SpeedTestEntity>

    @Query("DELETE FROM speed_test")
    suspend fun deleteAllSpeedTestResults()
}