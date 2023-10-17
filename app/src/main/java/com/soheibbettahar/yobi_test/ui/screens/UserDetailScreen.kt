package com.soheibbettahar.yobi_test.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.Male
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.soheibbettahar.yobi_test.R
import com.soheibbettahar.yobi_test.data.model.Location
import com.soheibbettahar.yobi_test.data.model.UserDetail
import com.soheibbettahar.yobi_test.ui.components.ErrorLayout
import com.soheibbettahar.yobi_test.ui.theme.Gray300
import com.soheibbettahar.yobi_test.ui.viewmodels.UserDetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    userDetailUiState: UserDetailUiState = UserDetailUiState.Loading,
    onRetry: () -> Unit = {},
    onNavigateUp: () -> Unit = {}
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Arrow back icon"
                        )
                    }
                }
            )
        }


    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            when (userDetailUiState) {

                is UserDetailUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )

                is UserDetailUiState.Success -> UserDetailLayout(userDetailUiState.data)

                is UserDetailUiState.Error -> ErrorLayout(
                    modifier = Modifier.align(Alignment.Center),
                    error = userDetailUiState.exception,
                    onRetryClick = onRetry,
                )
            }

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
            .verticalScroll(state = rememberScrollState())
            .padding(top = 16.dp)
    ) {

        AsyncImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(120.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current).data(userDetail.picture)
                .crossfade(true).build(),
            contentDescription = stringResource(id = R.string.user_image),
            contentScale = ContentScale.Crop,
            placeholder = ColorPainter(Gray300),
            error = ColorPainter(Gray300)
        )


        Spacer(modifier = Modifier.height(42.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.personal_info),
            style = MaterialTheme.typography.labelLarge
        )

        InfoItem(
            title = stringResource(R.string.full_name),
            content = userDetail.fullName,
            icon = Icons.Outlined.Person,
            contentDescription = stringResource(R.string.person_icon)
        )

        InfoItem(
            title = stringResource(R.string.phone_number),
            content = userDetail.phone,
            icon = Icons.Outlined.Phone,
            contentDescription = stringResource(R.string.phone_number_icon)
        )

        InfoItem(
            title = stringResource(R.string.email_address),
            content = userDetail.email,
            icon = Icons.Outlined.Email,
            contentDescription = stringResource(R.string.mail_icon)
        )

        InfoItem(
            title = stringResource(R.string.date_of_birth),
            content = userDetail.dateOfBirth,
            icon = Icons.Outlined.Cake,
            contentDescription = stringResource(R.string.cake_icon)
        )

        InfoItem(
            title = stringResource(R.string.gender),
            content = userDetail.gender,
            icon = Icons.Outlined.Male,
            contentDescription = stringResource(R.string.gender_icon)
        )

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.location),
            style = MaterialTheme.typography.labelLarge
        )

        InfoItem(
            title = stringResource(R.string.address),
            content = userDetail.location.street,
            icon = Icons.Outlined.Home,
            contentDescription = stringResource(R.string.home_icon)
        )

        InfoItem(
            title = stringResource(R.string.city),
            content = "${userDetail.location.state}, ${userDetail.location.city}",
            icon = Icons.Outlined.LocationCity,
            contentDescription = stringResource(R.string.city_icon)
        )

        InfoItem(
            title = stringResource(R.string.country),
            content = userDetail.location.country,
            icon = Icons.Outlined.Flag,
            contentDescription = stringResource(R.string.flag_icon)
        )

        InfoItem(
            title = stringResource(R.string.time_zone),
            content = userDetail.location.timezone,
            icon = Icons.Outlined.Schedule,
            contentDescription = stringResource(R.string.clock_icon)
        )


    }

}

@Composable
fun InfoItem(title: String, content: String, icon: ImageVector, contentDescription: String) {
    ListItem(headlineContent = { Text(text = content) },
        overlineContent = { Text(title) },
        leadingContent = { Icon(imageVector = icon, contentDescription = contentDescription) }

    )
}

@Preview
@Composable
fun InfoItemPreview() {
    InfoItem(
        title = "Phone number",
        content = "+45 92694011",
        icon = Icons.Outlined.Phone,
        contentDescription = "Phone number icon"
    )
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

