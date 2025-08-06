package id.assessment.data.users.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.assessment.core.network.interceptor.AuthInterceptor
import id.assessment.data.users.service.UsersApiConst
import id.assessment.data.users.service.UsersApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsersApiModule {

    @Singleton
    @Provides
    fun provideUsersApiService(): UsersApiService {
        val moshi = Moshi.Builder().build()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(UsersApiConst.PERSONAL_ACCESS_TOKEN))
            .build()

        return Retrofit.Builder()
            .baseUrl(UsersApiConst.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(UsersApiService::class.java)
    }

}