package id.assessment.data.users.model.response

import com.squareup.moshi.Json

data class UserDataResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "items")
    val items: List<UserResponse>
)
