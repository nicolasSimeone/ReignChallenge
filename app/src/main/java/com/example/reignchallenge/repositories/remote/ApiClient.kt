package com.example.reignchallenge.repositories.remote

import com.example.reignchallenge.model.hits
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("/api/v1/search_by_date?query=android")
    fun getNoticeList(): Deferred<Response<hits>>
}