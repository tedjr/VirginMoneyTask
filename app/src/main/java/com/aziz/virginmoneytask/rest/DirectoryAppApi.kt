package com.aziz.virginmoneytask.rest

import com.aziz.virginmoneytask.model.PeopleItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectoryAppApi {

    @GET("api/v1/people")
    suspend fun peopleList(): Response<PeopleItem>


    companion object {
        const val BASE_URL = "https://61d6afbe35f71e0017c2e74b.mockapi.io/"
        private const val QUERY_RESULTS = "results"
    }
}