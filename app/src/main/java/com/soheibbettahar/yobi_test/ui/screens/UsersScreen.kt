package com.soheibbettahar.yobi_test.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.soheibbettahar.yobi_test.R
import com.soheibbettahar.yobi_test.data.model.User
import com.soheibbettahar.yobi_test.ui.theme.Gray300

@Composable
fun UsersScreen() {
    Text(text = "Users Screen")
}


@Composable
@Preview(showBackground = true)
fun UsersScreenPreview() {
    UsersScreen()
}


@Composable
fun UserItem(modifier: Modifier = Modifier, user: User, onClick: (User) -> Unit = {}) {
    ListItem(
        modifier = modifier.clickable { onClick(user) },
        headlineContent = {
            Text(
                text = user.fullName,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        leadingContent = {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current).data(user.picture)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.user_image),
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Gray300),
                error = ColorPainter(Gray300)
            )

        }
    )
}


@Composable
@Preview(showBackground = true)
fun UserItemPreview() {
    UserItem(user = USER)
}


private val USER =
    User(
        "0",
        firstName = "Soheib",
        lastName = "Bettahar",
        title = "Mr",
        picture = "https://randomuser.me/api/portraits/med/men/38.jpg"
    )
