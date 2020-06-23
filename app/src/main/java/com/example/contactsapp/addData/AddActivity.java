package com.example.contactsapp.addData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.contactsapp.R;
import com.example.contactsapp.data.Contacts;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_DATA_NAME = "extra_task_name";
    public static final String EXTRA_DATA_PHONE = "extra_task_phone";

    private addViewModel mAddViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Bundle extras = getIntent().getExtras();
        mAddViewModel = new ViewModelProvider(this).get(addViewModel.class);

        final EditText Name = findViewById(R.id.newName);
        final EditText Phone = findViewById(R.id.newPhone);
        final EditText Age = findViewById(R.id.newAge);
        final EditText City = findViewById(R.id.newCity);
        final EditText College = findViewById(R.id.newCollege);
        final EditText Email = findViewById(R.id.newEmail);
        RadioGroup GenderGroup = findViewById(R.id.gender);
        RadioButton radioButton = findViewById(R.id.radioButton1);
        RadioButton radioButton1 = findViewById(R.id.radioButton2);
        RadioButton radioButton2 = findViewById(R.id.radioButton3);
        Button submit = findViewById(R.id.submit);

        if (extras!=null){
            String ContactName = extras.getString(EXTRA_DATA_NAME,"");
            String ContactPhone = extras.getString(EXTRA_DATA_PHONE, "");

            if(!ContactName.isEmpty()){
                Name.setText(ContactName);
            }

            if(!ContactPhone.isEmpty()){
                Phone.setText(ContactPhone);
            }

            submit.setText("Update");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String phone = Phone.getText().toString();
                if(!name.isEmpty() && !phone.isEmpty()){
                    if(extras!=null){
                        Contacts contacts = new Contacts(name, phone);
                        mAddViewModel.updateContacts(contacts);
                    }
                    else{
                        Contacts contacts = new Contacts(name, phone);
                        mAddViewModel.insertContact(contacts);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Missed an input",Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
