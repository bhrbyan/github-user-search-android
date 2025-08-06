package id.assessment.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.assessment.core.database.dao.UserDao
import id.assessment.core.database.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME: String = "githubusersearch.db"
    }

}