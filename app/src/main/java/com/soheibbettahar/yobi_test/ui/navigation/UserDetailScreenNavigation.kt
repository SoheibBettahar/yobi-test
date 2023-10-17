package com.soheibbettahar.yobi_test.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.soheibbettahar.yobi_test.ui.screens.UserDetailScreen
import com.soheibbettahar.yobi_test.ui.viewmodels.UserDetailViewModel

const val UserDetailRoute = "UserDetail"

private const val USER_ID_ARG = "userId"

fun NavGraphBuilder.userDetailScreen(onNavigateUp: () -> Unit) {

    composable(
        route = "$UserDetailRoute/{$USER_ID_ARG}",
        arguments = listOf(navArgument(USER_ID_ARG) { type = NavType.StringType })
    ) {
        val viewModel: UserDetailViewModel = hiltViewModel()
        val userDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()

        UserDetailScreen(
            userDetailUiState = userDetailUiState,
            onRetry = viewModel::fetchUserDetail,
            onNavigateUp = onNavigateUp
        )
    }

}

fun NavHostController.navigateToUserDetailScreen(userId: String) {
    val route = "$UserDetailRoute/$userId"
    navigateSingleTopTo(route)
}


internal class UserDetailArgs private constructor(val userId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this((checkNotNull(savedStateHandle[USER_ID_ARG]) as String))
}