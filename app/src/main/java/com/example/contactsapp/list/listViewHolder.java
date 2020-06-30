package com.example.contactsapp.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsapp.R;
import com.example.contactsapp.data.Contacts;

public class listViewHolder extends RecyclerView.ViewHolder {

    private TextView NameTv, PhoneTv;
    private ImageView imageView;

    public listViewHolder(@NonNull View itemView) {
        super(itemView);
        NameTv = itemView.findViewById(R.id.Name);
        PhoneTv = itemView.findViewById(R.id.Phone);
        imageView = itemView.findViewById(R.id.img);
    }

    public void bind(Contacts contacts){
        NameTv.setText(contacts.getName());
        PhoneTv.setText(contacts.getNumber());
        String gender = contacts.getGender();
        if(gender.equals("Female")){
            imageView.setImageResource(R.drawable.girl);
        }
        else
            imageView.setImageResource(R.drawable.boy);
    }
}
