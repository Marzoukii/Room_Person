package com.example.roomperson.Room

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomperson.model.Person


@Database(
    entities = [Person::class],
    version = 1
)
abstract class PersonDB : RoomDatabase(){

    abstract fun PersonDao() : PersonDao

    companion object {

        @Volatile private var instance : PersonDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PersonDB::class.java,
            "Person1.db"
        ).build()

    }
}