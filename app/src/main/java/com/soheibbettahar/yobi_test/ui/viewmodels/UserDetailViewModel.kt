package com.soheibbettahar.yobi_test.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soheibbettahar.yobi_test.data.model.UserDetail
import com.soheibbettahar.yobi_test.data.repository.UserRepository
import com.soheibbettahar.yobi_test.ui.navigation.UserDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

sealed interface UserDetailUiState {
    data class Success(val data: UserDetail) : UserDetailUiState
    data class Error(val exception: Throwable? = null) : UserDetailUiState
    object Loading : UserDetailUiState
}

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository
) : ViewModel() {

    private val args = UserDetailArgs(savedStateHandle)
    private val userId = args.userId

    private val _userDetailUiState = MutableStateFlow<UserDetailUiState>(UserDetailUiState.Loading)
    val userDetailUiState = _userDetailUiState.asStateFlow()

    init {
        fetchUserDetail()
    }


    fun fetchUserDetail() {
            userRepository
                .fetchUserDetail(userId = userId)
                .map { userDetail -> _userDetailUiState.value = UserDetailUiState.Success(userDetail) }
                .onStart { _userDetailUiState.value = UserDetailUiState.Loading }
                .catch { throwable ->
                    _userDetailUiState.value = UserDetailUiState.Error(throwable)
                }.launchIn(viewModelScope)
    }

}