package com.soheibbettahar.yobi_test.data.network

import retrofit2.http.GET
import retrofit2.http.Query


interface UserService {

    @GET("users")
    suspend fun fetchUsers(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): NetworkUsersPage

}
