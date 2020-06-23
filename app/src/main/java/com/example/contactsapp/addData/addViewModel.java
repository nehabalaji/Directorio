package com.example.contactsapp.addData;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.contactsapp.data.Contacts;
import com.example.contactsapp.database.ContactsRepository;

public class addViewModel extends AndroidViewModel {

    private ContactsRepository contactsRepository;
    private LiveData<PagedList<Contacts>> pagedListLiveData;

    public addViewModel(@NonNull Application application) {
        super(application);
        contactsRepository = new ContactsRepository(application);
    }

    public void insertContact(Contacts contacts){
        contactsRepository.insertContact(contacts);
    }

    public void updateContacts(Contacts contacts){
        contactsRepository.updateContact(contacts);
    }

}
