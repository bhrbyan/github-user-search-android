package id.assessment.feature.users.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.assessment.data.users.model.User
import id.assessment.data.users.repository.UsersRepository
import id.assessment.feature.users.list.UsersViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(UserDetailViewState())
    val viewState: StateFlow<UserDetailViewState> = _viewState.asStateFlow()

    fun getUserDetail(userId: Int) {
        viewModelScope.launch {
            _viewState.update { it.copy(loading = true) }

            val user = usersRepository.getUserDetail(userId).firstOrNull()
            val favorites = user?.let {
                usersRepository.getFavoritesUser(it.userId).firstOrNull()
            }

            _viewState.update {
                it.copy(
                    loading = false,
                    user = favorites?.firstOrNull() ?: user,
                    isFavorite = favorites?.isNotEmpty() == true
                )
            }
        }
    }

    fun saveUserDetail(user: User?) {
        viewModelScope.launch {
            user?.let {
                usersRepository.saveUserDetail(user)
                _viewState.update {
                    it.copy(
                        isFavorite = true
                    )
                }
            }
        }
    }

    fun deleteUserDetail(user: User?) {
        viewModelScope.launch {
            user?.let {
                usersRepository.deleteUserDetail(user)
                _viewState.update {
                    it.copy(
                        isFavorite = false
                    )
                }
            }
        }
    }

}