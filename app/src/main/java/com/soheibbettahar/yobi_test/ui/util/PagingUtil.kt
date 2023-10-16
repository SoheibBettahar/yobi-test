package com.soheibbettahar.yobi_test.ui.util

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.soheibbettahar.yobi_test.data.model.User

fun LazyPagingItems<User>.isRefreshSuccess() =
    loadState.refresh is LoadState.NotLoading && itemCount != 0

fun LazyPagingItems<User>.isRefreshEmpty() = loadState.source.refresh is LoadState.NotLoading
        && itemCount == 0

fun LazyPagingItems<User>.isRefreshLoading() = loadState.refresh == LoadState.Loading

fun LazyPagingItems<User>.isRefreshError(): Boolean = loadState.refresh is LoadState.Error

fun LazyPagingItems<User>.isAppendLoading() = loadState.append is LoadState.Loading

fun LazyPagingItems<User>.isAppendError() = loadState.append is LoadState.Error

fun LazyPagingItems<User>.appendError(): LoadState.Error =
    (loadState.append as? LoadState.Error) ?: (loadState.refresh as LoadState.Error)

fun LazyPagingItems<User>.refreshError(): LoadState.Error = loadState.refresh as LoadState.Error