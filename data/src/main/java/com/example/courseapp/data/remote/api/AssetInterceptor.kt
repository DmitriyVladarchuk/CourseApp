package com.example.courseapp.data.remote.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

class AssetInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val fileName = "courses.json"
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)

            Response.Builder()
                .code(200)
                .message(json)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(json.toResponseBody("application/json".toMediaType()))
                .build()
        } catch (e: IOException) {
            Response.Builder()
                .code(404)
                .message(e.message ?: "Unknown error")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build()
        }
    }
}