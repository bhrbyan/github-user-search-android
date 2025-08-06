package id.assessment.feature.users.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.assessment.data.users.repository.UsersRepository
import id.assessment.feature.users.list.UsersViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {

    private val _viewState = MutableStateFlow(UserDetailViewState())
    val viewState: StateFlow<UserDetailViewState> = _viewState.asStateFlow()

    fun getUserDetail(userId: Int) {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    loading = true
                )
            }

            usersRepository.getUserDetail(userId).collect { user ->
                _viewState.update {
                    it.copy(
                        loading = false,
                        user = user
                    )
                }
            }
        }
    }

}