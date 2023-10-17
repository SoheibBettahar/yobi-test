package com.soheibbettahar.yobi_test.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.soheibbettahar.yobi_test.data.model.User
import com.soheibbettahar.yobi_test.data.network.UserService
import com.soheibbettahar.yobi_test.data.network.asExternalModel
import okio.IOException
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 0
const val PAGE_SIZE = 20

class UsersPagingSource(private val searchText: String, private val userService: UserService) :
    PagingSource<Int, User>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val searchResponse = userService.fetchUsers(page, PAGE_SIZE)

            val total = searchResponse.total ?: 0
            val isNexPageAvailable = (page + 1) * PAGE_SIZE < total

            val users = searchResponse.users.map { it.asExternalModel() }
                .filter {
                    searchText.isEmpty() || it.fullName.lowercase().contains(searchText.lowercase())
                }

            LoadResult.Page(
                data = users,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (isNexPageAvailable) page + 1 else null
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

}