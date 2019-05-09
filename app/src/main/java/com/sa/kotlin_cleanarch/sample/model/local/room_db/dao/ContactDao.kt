package com.sa.kotlin_cleanarch.sample.model.local.room_db.dao

import android.provider.Settings
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sa.kotlin_cleanarch.sample.model.bean.Contact


/* Created by Sahil Bharti on 26/4/19.
 *
*/
@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contacts: ArrayList<Contact>)

    @Query("SELECT * FROM Contact WHERE id = :id LIMIT 1")
    fun retrieve(id: Int): LiveData<Contact>

    @Query("SELECT * FROM Contact")
    fun retrieveAllContact(): LiveData<List<Contact>>
}
