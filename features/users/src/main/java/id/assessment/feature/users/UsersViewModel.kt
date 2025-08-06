package id.assessment.feature.users

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.assessment.data.users.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {

    private val _viewState = MutableStateFlow(UsersViewState())
    val viewState: StateFlow<UsersViewState> = _viewState.asStateFlow()

    fun onSearchUsers(query: String) {
        viewModelScope.launch {
            _viewState.update { state ->
                state.copy(
                    searchQuery = query,
                    loading = true
                )
            }

            usersRepository.searchUsers(query)
                .collect { users ->
                    _viewState.update { state ->
                        val updatedState = state.copy(
                            searchQuery = query,
                            loading = false,
                            users = users
                        )
                        updatedState
                    }
                }
        }
    }
}