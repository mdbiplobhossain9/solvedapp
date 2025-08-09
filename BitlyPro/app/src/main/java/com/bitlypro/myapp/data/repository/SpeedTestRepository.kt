package com.bitlypro.myapp.data.repository

import com.bitlypro.myapp.data.database.SpeedTestDao
import com.bitlypro.myapp.data.database.SpeedTestEntity
import com.bitlypro.myapp.data.network.SpeedTestService
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpeedTestRepository @Inject constructor(
    private val speedTestDao: SpeedTestDao,
    private val speedTestService: SpeedTestService
) {
    
    fun getAllResults(): Flow<List<SpeedTestEntity>> = speedTestDao.getAllResults()
    
    fun getRecentResults(limit: Int): Flow<List<SpeedTestEntity>> = speedTestDao.getRecentResults(limit)
    
    suspend fun runSpeedTest(): SpeedTestEntity {
        val result = speedTestService.runSpeedTest()
        val entity = SpeedTestEntity(
            downloadSpeed = result.downloadSpeed,
            uploadSpeed = result.uploadSpeed,
            ping = result.ping,
            testDate = Date(),
            serverLocation = "Auto-selected",
            connectionType = "WiFi" // Could be determined dynamically
        )
        speedTestDao.insertResult(entity)
        return entity
    }
    
    suspend fun deleteResult(result: SpeedTestEntity) {
        speedTestDao.deleteResult(result)
    }
    
    suspend fun deleteAllResults() {
        speedTestDao.deleteAllResults()
    }
    
    suspend fun getAverageDownloadSpeed(): Double? = speedTestDao.getAverageDownloadSpeed()
    
    suspend fun getAverageUploadSpeed(): Double? = speedTestDao.getAverageUploadSpeed()
    
    suspend fun getAveragePing(): Long? = speedTestDao.getAveragePing()
}
    }
}