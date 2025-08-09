package com.bitlypro.myapp.data.repository

import com.bitlypro.myapp.data.database.SpeedTestDao
import com.bitlypro.myapp.data.database.SpeedTestEntity
import com.bitlypro.myapp.data.network.SpeedTestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpeedTestRepository(
    private val speedTestDao: SpeedTestDao,
    private val speedTestService: SpeedTestService
) {
    suspend fun performSpeedTest(): SpeedTestEntity {
        return withContext(Dispatchers.IO) {
            val result = speedTestService.testSpeed()
            val speedTestEntity = SpeedTestEntity(
                id = 0, // Assuming auto-increment in Room
                downloadSpeed = result.downloadSpeed,
                uploadSpeed = result.uploadSpeed,
                timestamp = System.currentTimeMillis()
            )
            speedTestDao.insert(speedTestEntity)
            speedTestEntity
        }
    }

    suspend fun getAllSpeedTests(): List<SpeedTestEntity> {
        return withContext(Dispatchers.IO) {
            speedTestDao.getAllSpeedTests()
        }
    }
}