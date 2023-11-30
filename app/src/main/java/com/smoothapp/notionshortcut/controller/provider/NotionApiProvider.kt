package com.smoothapp.notionshortcut.controller.provider

import com.smoothapp.notionshortcut.controller.util.NotionApiPostPageUtil
import com.smoothapp.notionshortcut.controller.util.SecretTestUtil
import com.smoothapp.notionshortcut.model.entity.NotionApiPostPageObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class NotionApiProvider {

    private fun getApiKey(): String{
        return SecretTestUtil.API_KEY
    }

    suspend fun postPage(requestBodyString: String): String {
        val requestBody = requestBodyString.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("https://api.notion.com/v1/pages")
            .addHeader("Notion-Version", "2022-06-28")
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer ${getApiKey()}")
            .addHeader("Content-Type", "application/json")
            .post(requestBody)
            .build()

        return getResponseBody(request)
    }

    suspend fun getAllObjects(): String {
        val requestBody = "".toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("https://api.notion.com/v1/search")
            .addHeader("Notion-Version", "2022-06-28")
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer ${getApiKey()}")
            .addHeader("Content-Type", "application/json")
            .post(requestBody)
            .build()

        return getResponseBody(request)
    }

    private suspend fun getResponseBody(request: Request) = withContext(Dispatchers.IO){
        val client = OkHttpClient()
        val response = client.newCall(request).execute()

        return@withContext response.body?.string().orEmpty()
    }
}