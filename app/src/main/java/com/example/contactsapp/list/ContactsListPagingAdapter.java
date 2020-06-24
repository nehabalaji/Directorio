package com.example.contactsapp.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsapp.R;
import com.example.contactsapp.data.Contacts;

public class ContactsListPagingAdapter extends PagedListAdapter<Contacts, listViewHolder> {
    private ClickListener clickListener;

    protected ContactsListPagingAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, final int position) {
        final Contacts currentContact = getItem(position);
        if(currentContact!=null){
            holder.bind(currentContact);
            if(clickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.itemClick(position, v);
                    }
                });
            }
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public interface ClickListener{
        void itemClick(int position,View view);
    }
    public  Contacts getContactAtPosition(int position){
        return getItem(position);
    }


    private static DiffUtil.ItemCallback<Contacts> DIFF_CALLBACK = new DiffUtil.ItemCallback<Contacts>() {
        @Override
        public boolean areItemsTheSame(@NonNull Contacts oldItem, @NonNull Contacts newItem) {
            return (oldItem.getName().equals(newItem.getName()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contacts oldItem, @NonNull Contacts newItem) {
            return oldItem.isContactEqual(newItem);
        }
    };
}
