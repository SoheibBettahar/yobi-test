package com.soheibbettahar.yobi_test.data.model

class UserDetail(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val gender: String,
    val email: String,
    val dateOfBirth: String,
    val phone: String,
    val location: Location
){
    val fullName: String
        get() = when {
            title.isEmpty() -> "$firstName $lastName"
            firstName.isEmpty() -> "$title.$lastName"
            else -> "$title.$firstName $lastName"
        }.trim()
}