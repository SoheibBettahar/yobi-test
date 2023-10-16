package com.soheibbettahar.yobi_test.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.soheibbettahar.yobi_test.data.PAGE_SIZE
import com.soheibbettahar.yobi_test.data.UsersPagingSource
import com.soheibbettahar.yobi_test.data.model.User
import com.soheibbettahar.yobi_test.data.network.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val dispatchIo: CoroutineDispatcher
) : UserRepository {
    override fun fetchUsers(): Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            UsersPagingSource(userService)
        }
    ).flow

}