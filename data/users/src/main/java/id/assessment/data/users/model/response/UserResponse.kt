package id.assessment.data.users.model.response

import com.squareup.moshi.Json

data class UserResponse(
    val login: String,
    val id: Int,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "html_url")
    val htmlUrl: String
)
