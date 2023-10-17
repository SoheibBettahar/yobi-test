package com.soheibbettahar.yobi_test.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.soheibbettahar.yobi_test.data.model.User
import com.soheibbettahar.yobi_test.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class UsersViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UserRepository
) : ViewModel() {

    var searchTerm by mutableStateOf("")
        private set

    @OptIn(FlowPreview::class)
    private val searchTermFlow: Flow<String> = snapshotFlow { searchTerm }
        .debounce(500)
        .map { it.trim() }
        .distinctUntilChanged()
        .conflate()

    val usersPagingDataFlow: Flow<PagingData<User>> = searchTermFlow.flatMapLatest {
        usersRepository.fetchUsers(it)
    }.distinctUntilChanged()
        .cachedIn(viewModelScope)


    fun onSearchTermChanged(text: String) {
        searchTerm = text
    }

}