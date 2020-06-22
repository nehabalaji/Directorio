package com.example.contactsapp.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.contactsapp.R;
import com.example.contactsapp.data.Contacts;

public class MainActivity extends AppCompatActivity {

    private listViewModel mListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewModel = new ViewModelProvider(this).get(listViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ContactsListPagingAdapter pagingAdapter = new ContactsListPagingAdapter();
        recyclerView.setAdapter(pagingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mListViewModel.pagedListLiveData.observe(this, new Observer<PagedList<Contacts>>() {
            @Override
            public void onChanged(PagedList<Contacts> contacts) {
                pagingAdapter.submitList(contacts);
            }
        });
    }
}
