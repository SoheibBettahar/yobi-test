package com.soheibbettahar.yobi_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.soheibbettahar.yobi_test.ui.navigation.UsersRoute
import com.soheibbettahar.yobi_test.ui.navigation.navigateToUserDetailScreen
import com.soheibbettahar.yobi_test.ui.navigation.userDetailScreen
import com.soheibbettahar.yobi_test.ui.navigation.usersScreen
import com.soheibbettahar.yobi_test.ui.theme.YobitestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            YobitestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    YobitestApp(modifier = Modifier.statusBarsPadding())
                }
            }
        }
    }

    @Composable
    private fun YobitestApp(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        YobitesNavHost(modifier = modifier, navController = navController)
    }


    @Composable
    private fun YobitesNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        startDestination: String = UsersRoute
    ) {

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {

            usersScreen { userId -> navController.navigateToUserDetailScreen(userId) }

            userDetailScreen(onNavigateUp = navController::navigateUp)

        }

    }


}
