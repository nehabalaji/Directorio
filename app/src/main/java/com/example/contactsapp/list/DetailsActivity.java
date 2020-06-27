package com.example.contactsapp.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.contactsapp.R;
import com.example.contactsapp.addData.AddActivity;
import com.example.contactsapp.data.Contacts;

public class DetailsActivity extends AppCompatActivity {
    TextView nameTV, numberTV, ageTV, emailTV, cityTV, collegeTV, genderTV;
    Contacts contacts;
    private final static int NEW_DATA_REQUEST_CODE =1;
    private final static int UPDATE_DATA_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_NAME = "extra_contact_name";
    public static final String EXTRA_DATA_PHONE = "extra_contact_phone";
    public static final String EXTRA_DATA_EMAIL = "extra_contact_email";
    public static final String EXTRA_DATA_AGE = "extra_contact_age";
    public static final String EXTRA_DATA_GENDER = "extra_contact_gender";
    public static final String EXTRA_DATA_CITY = "extra_contact_city";
    public static final String EXTRA_DATA_COLLEGE = "extra_contact_college";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameTV = findViewById(R.id.name);
        numberTV = findViewById(R.id.phone);
        ageTV = findViewById(R.id.age);
        emailTV = findViewById(R.id.email);
        cityTV = findViewById(R.id.city);
        collegeTV = findViewById(R.id.college);
        genderTV = findViewById(R.id.gender);


        final Bundle extras = getIntent().getExtras();

        if (extras!=null) {
            String ContactName = extras.getString(EXTRA_DATA_NAME, "");
            String ContactPhone = extras.getString(EXTRA_DATA_PHONE, "");
            String ContactEmail = extras.getString(EXTRA_DATA_EMAIL, "");
            String ContactAge = extras.getString(EXTRA_DATA_AGE, "");
            String ContactGender = extras.getString(EXTRA_DATA_GENDER, "");
            String ContactCity = extras.getString(EXTRA_DATA_CITY, "");
            String ContactCollege = extras.getString(EXTRA_DATA_COLLEGE, "");

            contacts = new Contacts(ContactName, ContactPhone, ContactEmail, ContactAge, ContactGender, ContactCity, ContactCollege);

            if(!ContactName.isEmpty()){
                nameTV.setText(ContactName);
            }

            if(!ContactPhone.isEmpty()){
                numberTV.setText(ContactPhone);
            }

            if(!ContactAge.isEmpty()){
                ageTV.setText(ContactAge);
            }

            if(!ContactGender.isEmpty()){
                genderTV.setText(ContactGender);
            }

            if(!ContactEmail.isEmpty()){
                emailTV.setText(ContactEmail);
            }

            if(!ContactCity.isEmpty()){
                cityTV.setText(ContactCity);
            }

            if(!ContactCollege.isEmpty()){
                collegeTV.setText(ContactCollege);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra(EXTRA_DATA_NAME, contacts.getName());
                intent.putExtra(EXTRA_DATA_PHONE,contacts.getNumber());
                intent.putExtra(EXTRA_DATA_EMAIL,contacts.getEmail());
                intent.putExtra(EXTRA_DATA_AGE,contacts.getAge());
                intent.putExtra(EXTRA_DATA_GENDER,contacts.getGender());
                intent.putExtra(EXTRA_DATA_CITY,contacts.getCity());
                intent.putExtra(EXTRA_DATA_COLLEGE,contacts.getCollege());

                startActivityForResult(intent, UPDATE_DATA_REQUEST_CODE);
                return true;

            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT,contacts.getName() +"\n"+ contacts.getNumber()
                + "\n" + contacts.getAge() + "\n" + contacts.getGender() +"\n" + contacts.getEmail() + "\n"
                + contacts.getCollege() + "\n" + contacts.getCity());

                startActivity(Intent.createChooser(sharingIntent, "contact Details"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void launchUpdateContact(Contacts contacts){

    }
}
