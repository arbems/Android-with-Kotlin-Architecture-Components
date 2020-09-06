package com.arbems.roomwithlivedata.data

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [User::class], version = 1)
class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}