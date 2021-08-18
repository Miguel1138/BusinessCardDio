package com.miguelsantos.businesscard.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM businesscard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Insert(onConflict = IGNORE)
    suspend fun insertCard(card: BusinessCard)

}