package com.soheibbettahar.yobi_test.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.soheibbettahar.yobi_test.data.model.User
import com.soheibbettahar.yobi_test.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow


class UsersViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UserRepository
) : ViewModel() {

    val usersPagingDataFlow: Flow<PagingData<User>> =
        usersRepository.fetchUsers().cachedIn(viewModelScope)

}