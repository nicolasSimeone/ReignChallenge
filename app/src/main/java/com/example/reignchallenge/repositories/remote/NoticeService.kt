package com.example.reignchallenge.repositories.remote

import com.example.reignchallenge.model.hits
import com.example.reignchallenge.utils.Result

class NoticeService (private val api: ApiClient) {

    suspend fun getNotices(): Result<hits> {

        val response = api.getNoticeList().await()
        val body = response.body()
        body?.let {
            return Result.Success(body)
        } ?: run {
            return Result.Error(
                Exception("error")
            )
        }
    }
}