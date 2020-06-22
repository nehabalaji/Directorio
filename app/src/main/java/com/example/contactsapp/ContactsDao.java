package com.example.contactsapp;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ContactsDao {

    @Insert
    void insertContact(Contacts contacts);

    @Update
    void updateContact(Contacts contacts);

    @Delete
    void deleteContact(Contacts contacts);

    @Query("Select * from Contacts ORDER BY Name asc")
    DataSource.Factory<Integer,Contacts> getAllContacts();
}
