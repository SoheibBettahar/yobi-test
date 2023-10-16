package com.soheibbettahar.yobi_test.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface UserService {

    @GET("user")
    suspend fun fetchUsers(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): NetworkUsersPage

    @GET("user/{id}")
    suspend fun fetchUserDetail(
        @Path("id") id: String,
    ): NetworkUserDetail


}
