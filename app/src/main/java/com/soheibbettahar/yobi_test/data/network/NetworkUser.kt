package com.soheibbettahar.yobi_test.data.network

import com.soheibbettahar.yobi_test.data.model.User
import com.squareup.moshi.Json

data class NetworkUser(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String?,
    @Json(name = "firstName") val firstName: String?,
    @Json(name = "lastName") val lastName: String?,
    @Json(name = "picture") val picture: String?
)

fun NetworkUser.asExternalModel(): User = User(
    id = id,
    title = title.orEmpty(),
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    picture = picture.orEmpty()
)