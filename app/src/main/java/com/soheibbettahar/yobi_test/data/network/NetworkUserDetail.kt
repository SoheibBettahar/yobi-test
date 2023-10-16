package com.soheibbettahar.yobi_test.data.network

import com.soheibbettahar.yobi_test.data.model.Location
import com.soheibbettahar.yobi_test.data.model.UserDetail
import com.squareup.moshi.Json

data class NetworkUserDetail(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String?,
    @Json(name = "firstName") val firstName: String?,
    @Json(name = "lastName") val lastName: String?,
    @Json(name = "picture") val picture: String?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "dateOfBirth") val dateOfBirth: String?,
    @Json(name = "phone") val phone: String?,
    @Json(name = "location") val location: NetworkLocation?,
)


fun NetworkUserDetail.toExternalModel(): UserDetail = UserDetail(
    id = id,
    title = title.orEmpty(),
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    picture = picture.orEmpty(),
    gender = gender.orEmpty(),
    email = email.orEmpty(),
    dateOfBirth = dateOfBirth.orEmpty(),
    phone = phone.orEmpty(),
    location = location?.toExternalModel() ?: Location()
)