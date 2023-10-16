package com.soheibbettahar.yobi_test.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //TODO: move appId to  a secrets.properties file later
        return chain.request().newBuilder()
            .addHeader("app-id", "623e2ae08615fdcb32f3fe8f")
            .build().let(chain::proceed)
    }
}
