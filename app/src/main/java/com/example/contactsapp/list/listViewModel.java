package com.example.contactsapp.list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.contactsapp.data.Contacts;
import com.example.contactsapp.database.ContactsRepository;

public class listViewModel extends AndroidViewModel {

    private ContactsRepository contactsRepository;
    LiveData<PagedList<Contacts>> pagedListLiveData;

    public listViewModel(@NonNull Application application) {
        super(application);
        contactsRepository = new ContactsRepository(application);
        pagedListLiveData = contactsRepository.getAllContacts();
    }

    public void insertTask(Contacts contacts){
        contactsRepository.insertContact(contacts);
    }

    public void deleteTask(Contacts contacts){
        contactsRepository.deleteContact(contacts);
    }
}
