package com.soheibbettahar.yobi_test.data.model

data class User(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String
) {
    val fullName: String
        get() = when{
            title.isEmpty() -> "$firstName $lastName"
            firstName.isEmpty() -> "$title.$lastName"
            else -> "$title.$firstName $lastName"
        }.trim()
}