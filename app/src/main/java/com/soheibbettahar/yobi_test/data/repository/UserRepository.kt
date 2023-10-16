package com.soheibbettahar.yobi_test.data.repository

import androidx.paging.PagingData
import com.soheibbettahar.yobi_test.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun fetchUsers(): Flow<PagingData<User>>

}