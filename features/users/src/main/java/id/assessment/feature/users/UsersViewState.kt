package id.assessment.feature.users

import id.assessment.data.users.model.User

data class UsersViewState(
    val loading: Boolean = false,
    val users: List<User> = emptyList(),
    val searchQuery: String = ""
)
