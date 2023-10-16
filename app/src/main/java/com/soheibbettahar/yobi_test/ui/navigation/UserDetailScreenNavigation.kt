package com.soheibbettahar.yobi_test.ui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.soheibbettahar.yobi_test.ui.screens.UserDetailScreen

const val UserDetailRoute = "UserDetail"

private const val USER_ID_ARG = "userId"

fun NavGraphBuilder.userDetailScreen() {

    composable(
        route = "$UserDetailRoute/{$USER_ID_ARG}",
        arguments = listOf(navArgument(USER_ID_ARG) { type = NavType.StringType })
    ) {
        UserDetailScreen()
    }

}

fun NavHostController.navigateToUserDetailScreen(userId: String) {
    val route = "$UserDetailRoute/$userId"
    navigateSingleTopTo(route)
}


internal class UserDetailArgs private constructor(val userId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this((checkNotNull(savedStateHandle[USER_ID_ARG]) as String))
}