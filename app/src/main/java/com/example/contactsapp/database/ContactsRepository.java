package com.example.contactsapp.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.contactsapp.data.Contacts;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ContactsRepository {

    private static ContactsRepository repository = null;
    private ContactsDao contactsDao;
    private static int PAGE_SIZE = 15;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ContactsRepository(Application application){
        ContactsDatabase database = ContactsDatabase.getInstance(application);
        contactsDao = database.contactsDao();
    }

    public static ContactsRepository getRepository(Application application){
        if (repository==null){
            synchronized (ContactsRepository.class){
                if (repository==null){
                    repository = new ContactsRepository(application);
                }
            }
        }
        return repository;
    }

    public void insertContact(final Contacts contacts){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactsDao.insertContact(contacts);
            }
        });
    }

    public void updateContact(final Contacts contacts){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactsDao.updateContact(contacts);

            }
        });
    }

    public void deleteContact(final Contacts contacts){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactsDao.deleteContact(contacts);
            }
        });
    }

    public LiveData<PagedList<Contacts>> getAllContacts(){
        return new LivePagedListBuilder<>(
                contactsDao.getAllContacts(), PAGE_SIZE
        ).build();
    }
}
