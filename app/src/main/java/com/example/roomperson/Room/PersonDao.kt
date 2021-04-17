package com.example.roomperson.Room

import android.content.Context
import android.provider.ContactsContract
import androidx.room.*
import com.example.roomperson.model.Person


@Dao
interface PersonDao {

    @Insert
    suspend fun addPerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)
    @Query("DELETE FROM person")
    suspend fun delete()

    @Query("SELECT * FROM person")
    suspend fun getPerson(): List<Person>
}