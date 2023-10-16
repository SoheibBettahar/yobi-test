package com.soheibbettahar.yobi_test.data.network

import com.soheibbettahar.yobi_test.data.model.Location
import com.squareup.moshi.Json

data class NetworkLocation(
    @Json(name = "street") val street: String?,
    @Json(name = "city") val city: String?,
    @Json(name = "state") val state: String?,
    @Json(name = "country") val country: String?,
    @Json(name = "timezone") val timezone: String?,
)


fun NetworkLocation.toExternalModel(): Location =
    Location(
        street = street.orEmpty(),
        city = city.orEmpty(),
        state = state.orEmpty(),
        country = country.orEmpty(),
        timezone = timezone.orEmpty()
    )
