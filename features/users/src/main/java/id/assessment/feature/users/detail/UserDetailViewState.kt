package id.assessment.feature.users.detail

import id.assessment.data.users.model.User

data class UserDetailViewState(
    val loading: Boolean = false,
    val user: User? = null,
    val isFavorite: Boolean? = false
)
