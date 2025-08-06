package id.assessment.data.users.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDataResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "items")
    val items: List<UserResponse>
)
