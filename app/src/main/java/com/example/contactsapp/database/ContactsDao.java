package com.example.contactsapp.database;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.contactsapp.data.Contacts;

@Dao
public interface ContactsDao {

    @Insert
    void insertContact(Contacts contacts);

    @Update
    void updateContact(Contacts contacts);

    @Delete
    void deleteContact(Contacts contacts);

    @Query("Select * from Contacts")
    DataSource.Factory<Integer,Contacts> getAllContacts();
}
