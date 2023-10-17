package com.soheibbettahar.yobi_test.data.repository

import androidx.paging.PagingData
import com.soheibbettahar.yobi_test.data.model.User
import com.soheibbettahar.yobi_test.data.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun fetchUsers(searchText: String): Flow<PagingData<User>>

    fun fetchUserDetail(userId: String): Flow<UserDetail>

}