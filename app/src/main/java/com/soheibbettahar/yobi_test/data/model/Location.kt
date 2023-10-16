package com.soheibbettahar.yobi_test.data.model

import java.util.TimeZone

data class Location(
    val street: String = "",
    val city: String = "",
    val state: String = "",
    val country: String = "",
    val timezone: String = "",
) {

    val isEmpty: Boolean
        get() = street.isEmpty()
                && city.isEmpty()
                && state.isEmpty()
                && country.isEmpty()
                && timezone.isEmpty()

}