package id.assessment.data.users.repository

import id.assessment.core.di.dispatcher.CoreDispatcher
import id.assessment.data.users.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRemoteRepository @Inject constructor(
    private val dispatcher: CoreDispatcher
) : UsersRepository {

    override fun searchUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}