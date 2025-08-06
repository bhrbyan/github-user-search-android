package id.assessment.data.users.model

import com.squareup.moshi.Json

data class User(
    val login: String,
    val id: Int,
    val nodeId: String? = null,
    val avatarUrl: String? = null,
    val gravatarId: String? = null,
    val url: String? = null,
    val htmlUrl: String? = null,
    val followersUrl: String? = null,
    val subscriptionsUrl: String? = null,
    val organizationsUrl: String? = null,
    val reposUrl: String? = null,
    val receivedEventsUrl: String? = null,
    val type: String? = null,
    val score: Int? = null,
    val followingUrl: String? = null,
    val gistsUrl: String? = null,
    val starredUrl: String? = null,
    val eventsUrl: String? = null,
    val siteAdmin: Boolean? = null,
    val name: String? = null,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val hireable: Boolean? = null,
    val bio: String? = null,
    val twitterUsername: String? = null,
    val publicRepos: Int? = null,
    val publicGists: Int? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
