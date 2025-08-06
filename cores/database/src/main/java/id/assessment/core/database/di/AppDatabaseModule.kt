package id.assessment.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.assessment.core.database.AppDatabase
import id.assessment.core.database.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(coreDatabase: AppDatabase): UserDao {
        return coreDatabase.userDao()
    }

}