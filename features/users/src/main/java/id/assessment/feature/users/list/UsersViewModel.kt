package id.assessment.feature.users.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.assessment.data.users.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    private val _viewState = MutableStateFlow(UsersViewState())
    val viewState: StateFlow<UsersViewState> = _viewState.asStateFlow()

    init {
        observeSearchQuery()
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(1000)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collectLatest { query ->
                    _viewState.update { it.copy(loading = true, searchQuery = query) }

                    usersRepository.searchUsers(query).collect { users ->
                        _viewState.update {
                            it.copy(
                                loading = false,
                                users = users
                            )
                        }
                    }
                }
        }
    }

    fun onQueryChange(query: String) {
        _searchQuery.value = query
        _viewState.update { it.copy(searchQuery = query) }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _viewState.update { it.copy(searchQuery = "", users = emptyList()) }
    }
}