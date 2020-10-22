package com.example.reignchallenge.repositories.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reignchallenge.model.NoticeDeleted
import com.example.reignchallenge.model.NoticesSaved

@Database(entities = [NoticeDeleted::class, NoticesSaved::class], version = 4)
abstract class AppDatabase : RoomDatabase(){
    abstract fun NoticeDeletedDAO() : NoticeDeletedDAO

    companion object{
        @Volatile
        private var INSTANCE:AppDatabase? = null
        private val ROOM_DB_NAME = "challenges.db"

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    ROOM_DB_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}