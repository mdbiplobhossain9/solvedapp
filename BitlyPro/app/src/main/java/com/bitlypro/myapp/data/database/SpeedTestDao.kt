package com.bitlypro.myapp.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeedTestDao {
    
    @Query("SELECT * FROM speed_test_results ORDER BY testDate DESC")
    fun getAllResults(): Flow<List<SpeedTestEntity>>
    
    @Query("SELECT * FROM speed_test_results ORDER BY testDate DESC LIMIT :limit")
    fun getRecentResults(limit: Int): Flow<List<SpeedTestEntity>>
    
    @Insert
    suspend fun insertResult(result: SpeedTestEntity)
    
    @Delete
    suspend fun deleteResult(result: SpeedTestEntity)
    
    @Query("DELETE FROM speed_test_results")
    suspend fun deleteAllResults()
    
    @Query("SELECT AVG(downloadSpeed) FROM speed_test_results")
    suspend fun getAverageDownloadSpeed(): Double?
    
    @Query("SELECT AVG(uploadSpeed) FROM speed_test_results")
    suspend fun getAverageUploadSpeed(): Double?
    
    @Query("SELECT AVG(ping) FROM speed_test_results")
    suspend fun getAveragePing(): Long?
}