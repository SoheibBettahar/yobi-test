package com.soheibbettahar.yobi_test.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soheibbettahar.yobi_test.data.model.Location
import com.soheibbettahar.yobi_test.data.model.UserDetail
import com.soheibbettahar.yobi_test.ui.components.ErrorLayout
import com.soheibbettahar.yobi_test.ui.viewmodels.UserDetailUiState

@Composable
fun UserDetailScreen(
    userDetailUiState: UserDetailUiState = UserDetailUiState.Loading,
    onRetry: () -> Unit = {}
) {

    Box(modifier = Modifier.fillMaxSize()) {

        when (userDetailUiState) {

            is UserDetailUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(
                    Alignment.Center
                )
            )

            is UserDetailUiState.Success -> UserDetailLayout(userDetailUiState.data)

            is UserDetailUiState.Error -> ErrorLayout(
                modifier = Modifier
                    .align(Alignment.Center),
                error = userDetailUiState.exception,
                onRetryClick = onRetry,
            )
        }

    }

}

@Composable
@Preview(showBackground = true)
fun UserDetailScreenPreview() {
    UserDetailScreen()
}


@Composable
fun UserDetailLayout(userDetail: UserDetail) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Text(text = "${userDetail.firstName}")
    }

}

@Preview
@Composable
fun UserDetailLayoutPreview() {
    UserDetailLayout(userDetail = userDetail)
}


private val userDetail = UserDetail(
    id = "60d0fe4f5311236168a109ca",
    title = "ms",
    firstName = "Sara",
    lastName = "Andersen",
    picture = "https://randomuser.me/api/portraits/women/58.jpg",
    gender = "female",
    email = "sara.andersen@example.com",
    dateOfBirth = "1996-04-30T19:26:49.610Z",
    phone = "92694011",
    location = Location(
        street = "9614, SÃ¸ndermarksvej",
        city = "Kongsvinger",
        state = "Nordjylland",
        country = "Denmark",
        timezone = "-9:00"
    )
)

