package id.assessment.data.users.repository

import android.util.Log
import id.assessment.core.di.dispatcher.CoreDispatcher
import id.assessment.data.users.model.User
import id.assessment.data.users.service.UsersApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UsersRemoteRepository @Inject constructor(
    private val apiService: UsersApiService,
    private val dispatcher: CoreDispatcher
) : UsersRepository {

    override suspend fun searchUsers(query: String): Flow<List<User>> = flow {
        val response = apiService.searchUsers(query)
        val users = response.items.map {
            User(
                it.login,
                it.id,
                it.nodeId,
                it.avatarUrl,
                it.gravatarId,
                it.url,
                it.htmlUrl,
                it.followersUrl,
                it.subscriptionsUrl,
                it.organizationsUrl,
                it.reposUrl,
                it.receivedEventsUrl,
                it.type,
                it.score,
                it.followingUrl,
                it.gistsUrl,
                it.starredUrl,
                it.eventsUrl,
                it.siteAdmin
            )
        }
        emit(users)
    }.catch { e ->
        Log.e("Repo", "Error: ${e.message}")
        emit(emptyList())
    }.flowOn(dispatcher.io)

}