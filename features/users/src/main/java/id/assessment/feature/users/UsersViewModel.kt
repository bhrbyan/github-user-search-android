package id.assessment.feature.users

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
    val usersRepository: UsersRepository
): ViewModel() {

    private val _viewState = MutableStateFlow(UsersViewState())
    val viewState: StateFlow<UsersViewState> = _viewState.asStateFlow()

    fun onSearchUsers(query: String) {
        _viewState.update { state ->
            state.copy(
                loading = false,
                searchQuery = query,
                surahList = emptyList()
            )
        }
    }
}