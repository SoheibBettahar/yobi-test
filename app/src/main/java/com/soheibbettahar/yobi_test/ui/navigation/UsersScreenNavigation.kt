package com.soheibbettahar.yobi_test.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.soheibbettahar.yobi_test.ui.screens.UsersScreen
import com.soheibbettahar.yobi_test.ui.viewmodels.UsersViewModel

const val UsersRoute = "Users"

fun NavGraphBuilder.usersScreen(onNavigateToUserDetailScreen: (userId: String) -> Unit) {

    composable(UsersRoute) {
        val viewModel: UsersViewModel = hiltViewModel()
        val usersPagingItems = viewModel.usersPagingDataFlow.collectAsLazyPagingItems()
        val searchText = viewModel.searchTerm

        UsersScreen(
            pagingItems = usersPagingItems,
            searchText = searchText,
            onSearchTextChanged = viewModel::onSearchTermChanged,
            onNavigateToUserDetailScreen = onNavigateToUserDetailScreen
        )
    }

}