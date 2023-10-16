package com.soheibbettahar.yobi_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.soheibbettahar.yobi_test.ui.screens.UsersScreen
import com.soheibbettahar.yobi_test.ui.theme.YobitestTheme
import com.soheibbettahar.yobi_test.ui.viewmodels.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YobitestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val usersViewModel: UsersViewModel = hiltViewModel()
                    val usersPagingItems = usersViewModel.usersPagingDataFlow.collectAsLazyPagingItems()
                    UsersScreen(usersPagingItems)
                }
            }
        }
    }




}
