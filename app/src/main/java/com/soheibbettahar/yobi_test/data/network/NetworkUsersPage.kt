package com.soheibbettahar.yobi_test.data.network

import com.squareup.moshi.Json

data class NetworkUsersPage(
    @Json(name = "data") val users: List<NetworkUser>,
    @Json(name = "total") val total: Int? = 0,
    @Json(name = "page") val page: Int? = 0,
    @Json(name = "limit") val limit: Int? = 0
)