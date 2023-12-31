package com.soheibbettahar.yobi_test.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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

private const val USER_ITEM_TYPE = 0
private const val FOOTER_ITEM_TYPE = 1

@Composable
fun UsersList(
    modifier: Modifier = Modifier,
    users: LazyPagingItems<User>,
    listState: LazyListState = rememberLazyListState(),
    isAppendLoading: Boolean = false,
    isAppendError: Boolean = false,
    onUserItemClick: (User) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {

    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp)
    ) {

        items(
            count = users.itemCount,
            key = users.itemKey(),
            contentType = { USER_ITEM_TYPE }) { index ->
            val user = users[index]
            if (user == null) PlaceHolderItem()
            else UserItem(user = user, onClick = onUserItemClick)
        }

        if (isAppendLoading || isAppendError) {
            item(contentType = { FOOTER_ITEM_TYPE }) {
                Footer(
                    isLoading = isAppendLoading, isError = isAppendError, retry = onRetryClick
                )
            }
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
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(user) },
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

@Composable
fun PlaceHolderItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(color = Gray300)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .size(height = 16.dp, width = 200.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = Gray300)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PlaceHolderItemPreview() {
    PlaceHolderItem()
}


@Composable
fun Footer(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
    isError: Boolean = false,
    retry: () -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        if (isLoading) CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 12.dp)
                .size(24.dp),
            strokeWidth = 2.dp,
        )

        if (isError)
            IconButton(modifier = Modifier.align(Alignment.Center), onClick = retry) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh"
                )
            }
    }
}


@Preview(showBackground = true)
@Composable
fun FooterPreview() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Footer(
            modifier = Modifier
                .background(Color.Green)
                .weight(1f),
            isLoading = true,
            isError = false
        )

        Footer(
            modifier = Modifier
                .background(Color.Red)
                .weight(1f), isLoading = false, isError = true
        )
    }
}


val users: List<User> = (1..20).map {
    User(
        id = it.toString(), firstName = "Soheib",
        lastName = "Bettahar",
        title = "Mr",
        picture = "https://randomuser.me/api/portraits/med/men/38.jpg"
    )
}