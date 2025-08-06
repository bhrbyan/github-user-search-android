package id.assessment.data.users.repository

import id.assessment.core.di.dispatcher.CoreDispatcher
import id.assessment.data.users.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersLocalRepository @Inject constructor(
    private val dispatcher: CoreDispatcher
) : UsersRepository {

    override suspend fun searchUsers(query: String): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDetail(userId: Int): Flow<User> {
        TODO("Not yet implemented")
    }
}