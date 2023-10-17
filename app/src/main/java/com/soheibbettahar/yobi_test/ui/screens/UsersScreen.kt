package com.soheibbettahar.yobi_test.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.soheibbettahar.yobi_test.R
import com.soheibbettahar.yobi_test.data.model.User
import com.soheibbettahar.yobi_test.ui.components.ErrorLayout
import com.soheibbettahar.yobi_test.ui.components.SearchTextField
import com.soheibbettahar.yobi_test.ui.components.UsersList
import com.soheibbettahar.yobi_test.ui.components.users
import com.soheibbettahar.yobi_test.ui.util.isAppendError
import com.soheibbettahar.yobi_test.ui.util.isAppendLoading
import com.soheibbettahar.yobi_test.ui.util.isRefreshEmpty
import com.soheibbettahar.yobi_test.ui.util.isRefreshError
import com.soheibbettahar.yobi_test.ui.util.isRefreshLoading
import com.soheibbettahar.yobi_test.ui.util.isRefreshSuccess
import com.soheibbettahar.yobi_test.ui.util.refreshError
import kotlinx.coroutines.flow.flowOf

@Composable
fun UsersScreen(
    pagingItems: LazyPagingItems<User>,
    searchText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    onNavigateToUserDetailScreen: (userId: String) -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {

        SearchTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            searchText = searchText,
            onTextChanged = onSearchTextChanged
        )

        Box(modifier = Modifier.fillMaxSize().imePadding()) {

            if (pagingItems.isRefreshSuccess()) {
                UsersList(
                    users = pagingItems,
                    isAppendLoading = pagingItems.isAppendLoading(),
                    isAppendError = pagingItems.isAppendError(),
                    onUserItemClick = { user -> onNavigateToUserDetailScreen(user.id) },
                    onRetryClick = pagingItems::retry
                )

            }

            if (pagingItems.isRefreshLoading()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            if (pagingItems.isRefreshError()) {
                ErrorLayout(
                    modifier = Modifier
                        .align(Alignment.Center),
                    error = pagingItems.refreshError().error,
                    onRetryClick = pagingItems::refresh,
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


