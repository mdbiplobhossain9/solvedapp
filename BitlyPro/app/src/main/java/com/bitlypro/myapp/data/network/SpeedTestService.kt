package com.bitlypro.myapp.data.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class SpeedTestService {

    private val client = OkHttpClient()

    @Throws(IOException::class)
    fun performSpeedTest(url: String): Long {
        val request = Request.Builder()
            .url(url)
            .build()

        val startTime = System.currentTimeMillis()
        val response: Response = client.newCall(request).execute()
        val endTime = System.currentTimeMillis()

        response.close()
        return endTime - startTime
    }
}