package com.soheibbettahar.yobi_test.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.soheibbettahar.yobi_test.R
import com.soheibbettahar.yobi_test.data.model.User
import com.soheibbettahar.yobi_test.ui.theme.Gray300
import kotlinx.coroutines.flow.flowOf

@Composable
fun UsersScreen(pagingItems: LazyPagingItems<User>) {

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
        Text(modifier = modifier, text = stringResource(R.string.an_error_happened), textAlign = TextAlign.Center)

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



@Composable
@Preview(showBackground = true)
fun UsersScreenPreview() {
    val pagingItems = flowOf(PagingData.from(users)).collectAsLazyPagingItems()
    UsersScreen(pagingItems)
}


@Composable
fun UsersList(
    modifier: Modifier = Modifier,
    users: LazyPagingItems<User>,
    listState: LazyListState = rememberLazyListState(),
    onUserItemClick: (User) -> Unit = {}
) {

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {

        items(count = users.itemCount, key = users.itemKey()) { index ->
            val user = users[index]
            if (user != null) UserItem(user = user, onClick = onUserItemClick)
        }

    }

}

@Composable
@Preview(showBackground = true)
fun UsersListPreview() {
    val pagingItems = flowOf(PagingData.from(users)).collectAsLazyPagingItems()
    UsersList(users = pagingItems)
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
    UserItem(user = users.first())
}


private val users: List<User> = (1..20).map {
    User(
        id = it.toString(), firstName = "Soheib",
        lastName = "Bettahar",
        title = "Mr",
        picture = "https://randomuser.me/api/portraits/med/men/38.jpg"
    )
}
