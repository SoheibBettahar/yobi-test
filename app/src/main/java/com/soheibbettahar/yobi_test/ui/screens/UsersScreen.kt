package com.soheibbettahar.yobi_test.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.soheibbettahar.yobi_test.R
import com.soheibbettahar.yobi_test.data.model.User
import com.soheibbettahar.yobi_test.ui.components.SearchTextField
import com.soheibbettahar.yobi_test.ui.components.UsersList
import com.soheibbettahar.yobi_test.ui.components.users
import com.soheibbettahar.yobi_test.ui.util.isAppendError
import com.soheibbettahar.yobi_test.ui.util.isAppendLoading
import com.soheibbettahar.yobi_test.ui.util.isRefreshEmpty
import com.soheibbettahar.yobi_test.ui.util.isRefreshError
import com.soheibbettahar.yobi_test.ui.util.isRefreshLoading
import com.soheibbettahar.yobi_test.ui.util.isRefreshSuccess
import kotlinx.coroutines.flow.flowOf

@Composable
fun UsersScreen(pagingItems: LazyPagingItems<User>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {

        SearchTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {

            if (pagingItems.isRefreshSuccess()) {
                UsersList(users = pagingItems,
                    isAppendLoading = pagingItems.isAppendLoading(),
                    isAppendError = pagingItems.isAppendError())
            }

            if (pagingItems.isRefreshLoading()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            if (pagingItems.isRefreshError()) {
                ErrorLayout(
                    modifier = Modifier
                        .align(Alignment.Center),
                    onRetryClick = { pagingItems.refresh() },
                )
            }


            if (pagingItems.isRefreshEmpty()) {
                EmptyStateLayout(modifier = Modifier.align(Alignment.Center))
            }

        }


    }

}


@Composable
@Preview(showBackground = true)
fun UsersScreenPreview() {
    val pagingItems = flowOf(PagingData.from(users)).collectAsLazyPagingItems()
    UsersScreen(pagingItems)
}


@Composable
fun EmptyStateLayout(modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = stringResource(R.string.no_users_available))
}

@Preview(showBackground = true)
@Composable
fun EmptyStateLayoutPreview() {
    EmptyStateLayout()
}


@Composable
fun ErrorLayout(modifier: Modifier = Modifier, onRetryClick: () -> Unit = {}) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = modifier,
            text = stringResource(R.string.an_error_happened),
            textAlign = TextAlign.Center
        )

        Button(
            modifier = modifier,
            onClick = onRetryClick,
        ) { Text(text = stringResource(R.string.retry)) }
    }

}

@Preview
@Composable
fun ErrorLayoutPreview() {
    ErrorLayout()
}

