package com.example.contactsapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contacts.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactsDao contactsDao();
    public static ContactsDatabase INSTANCE = null;
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static ContactsDatabase getInstance(final Context context){
        if(INSTANCE==null){
            synchronized (INSTANCE){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactsDatabase.class,
                            "ContactsDatabase").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
