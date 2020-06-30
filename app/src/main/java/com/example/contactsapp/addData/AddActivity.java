package com.example.contactsapp.addData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactsapp.R;
import com.example.contactsapp.data.Contacts;
import com.example.contactsapp.list.DetailsActivity;
import com.example.contactsapp.list.MainActivity;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_DATA_ID = "extra_data_id";
    public static final String EXTRA_DATA_NAME = "extra_contact_name";
    public static final String EXTRA_DATA_PHONE = "extra_contact_phone";
    public static final String EXTRA_DATA_EMAIL = "extra_contact_email";
    public static final String EXTRA_DATA_AGE = "extra_contact_age";
    public static final String EXTRA_DATA_GENDER = "extra_contact_gender";
    public static final String EXTRA_DATA_CITY = "extra_contact_city";
    public static final String EXTRA_DATA_COLLEGE = "extra_contact_college";

    private addViewModel mAddViewModel;
    RadioButton radioButton;
    int imageID;
    long ID;

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
        final RadioGroup GenderGroup = findViewById(R.id.gender);
        RadioButton male = findViewById(R.id.radioButton1);
        RadioButton female = findViewById(R.id.radioButton2);

        TextView submit = findViewById(R.id.submit);

        if (extras!=null){
            String ContactName = extras.getString(EXTRA_DATA_NAME,"");
            String ContactPhone = extras.getString(EXTRA_DATA_PHONE, "");
            String ContactEmail = extras.getString(EXTRA_DATA_EMAIL, "");
            String ContactAge = extras.getString(EXTRA_DATA_AGE, "");
            String ContactGender = extras.getString(EXTRA_DATA_GENDER, "");
            String ContactCity = extras.getString(EXTRA_DATA_CITY, "");
            String ContactCollege = extras.getString(EXTRA_DATA_COLLEGE, "");


            if(ContactGender.equals("Female")){
                imageID = R.drawable.girl;
            }
            else
                imageID = R.drawable.girl;

            if(!ContactName.isEmpty()){
                Name.setText(ContactName);
            }

            if(!ContactPhone.isEmpty()){
                Phone.setText(ContactPhone);
            }

            if(!ContactAge.isEmpty()){
                Age.setText(ContactAge);
            }

            if(!ContactGender.isEmpty()){
               if (ContactGender.equals("Male")){
                   male.setChecked(true);
                }
                else
                    female.setChecked(true);
            }

            if(!ContactEmail.isEmpty()){
                Email.setText(ContactEmail);
            }

            if(!ContactCity.isEmpty()){
                City.setText(ContactCity);
            }

            if(!ContactCollege.isEmpty()){
                College.setText(ContactCollege);
                College.setSelection(ContactCollege.length());
                College.requestFocus();
            }


            submit.setText("Update");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String phone = Phone.getText().toString();
                String email = Email.getText().toString();
                String age = Age.getText().toString();
                int genderId = GenderGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(genderId);
                String gender = radioButton.getText().toString();
                String city = City.getText().toString();
                String college = College.getText().toString();
                if(!name.isEmpty() && !phone.isEmpty() && !age.isEmpty() && !gender.isEmpty() && !city.isEmpty() && !college.isEmpty() && !email.isEmpty()){
                    if(extras!=null && extras.containsKey(EXTRA_DATA_ID)){
                        ID = extras.getLong(EXTRA_DATA_ID, 0L);
                        Contacts contacts = new Contacts(name, phone, email, age, gender, city, college, imageID, ID);
                        Log.e("TAG", contacts.getName());
                        mAddViewModel.updateContacts(contacts);
                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Contacts contacts = new Contacts(name, phone, email, age, gender, city, college, imageID, ID);
                        mAddViewModel.insertContact(contacts);
                        Toast.makeText(getApplicationContext(), "New Contact Added", Toast.LENGTH_SHORT).show();
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
