package com.example.contactsapp.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contactsapp.AddActivity;
import com.example.contactsapp.R;
import com.example.contactsapp.data.Contacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private final static int NEW_DATA_REQUEST_CODE =1;
    private final static int UPDATE_DATA_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_NAME = "extra_task_name";
    public static final String EXTRA_DATA_PHONE = "extra_task_phone";

    public listViewModel mListViewModel;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent,NEW_DATA_REQUEST_CODE);
            }
        });

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

        final ConstraintLayout constraint = findViewById(R.id.constraint_layout);
        final Snackbar snackbar = Snackbar.make(constraint,"Task Deleted", BaseTransientBottomBar.LENGTH_SHORT)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListViewModel.insertContact(contacts);
                    }
                });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                contacts = pagingAdapter.getContactAtPosition(pos);
                mListViewModel.deleteContact(contacts);
                snackbar.show();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        pagingAdapter.setOnItemClickListener(new ContactsListPagingAdapter.ClickListener() {
            @Override
            public void itemClick(int position, View view) {
                Contacts currentContact = pagingAdapter.getContactAtPosition(position);
                launchUpdateContactActivity(currentContact);
            }
        });
    }
    private void launchUpdateContactActivity(Contacts currentContact) {
        Intent intent = new Intent(this,AddActivity.class);
        intent.putExtra(EXTRA_DATA_NAME,currentContact.getName());
        intent.putExtra(EXTRA_DATA_PHONE,currentContact.getNumber());
        startActivityForResult(intent,UPDATE_DATA_REQUEST_CODE);
    }
}
