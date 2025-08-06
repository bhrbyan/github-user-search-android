package id.assessment.data.users.service

import id.assessment.data.users.model.response.UserDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApiService {
    @GET(UsersApiConst.PATH_SEARCH_USERS)
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
    ): UserDataResponse
}