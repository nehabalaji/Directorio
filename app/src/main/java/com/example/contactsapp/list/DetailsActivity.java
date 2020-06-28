package com.example.contactsapp.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactsapp.R;
import com.example.contactsapp.addData.AddActivity;
import com.example.contactsapp.data.Contacts;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class DetailsActivity extends AppCompatActivity {
    ActionBar actionBar;
    TextView nameTV, numberTV, ageTV, emailTV, cityTV, collegeTV, genderTV;
    ImageView imageView;
    Contacts contacts;
    private final static int NEW_DATA_REQUEST_CODE = 1;
    private final static int UPDATE_DATA_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_NAME = "extra_contact_name";
    public static final String EXTRA_DATA_PHONE = "extra_contact_phone";
    public static final String EXTRA_DATA_EMAIL = "extra_contact_email";
    public static final String EXTRA_DATA_AGE = "extra_contact_age";
    public static final String EXTRA_DATA_GENDER = "extra_contact_gender";
    public static final String EXTRA_DATA_CITY = "extra_contact_city";
    public static final String EXTRA_DATA_COLLEGE = "extra_contact_college";

    ExtendedFloatingActionButton button;

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
        imageView = findViewById(R.id.image);
        button = findViewById(R.id.callButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(numberTV.toString()));

                if (ActivityCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#F1F1F1"));
        actionBar.setBackgroundDrawable(colorDrawable);

        final Bundle extras = getIntent().getExtras();

        if (extras!=null) {
            String ContactName = extras.getString(EXTRA_DATA_NAME, "");
            String ContactPhone = extras.getString(EXTRA_DATA_PHONE, "");
            String ContactEmail = extras.getString(EXTRA_DATA_EMAIL, "");
            String ContactAge = extras.getString(EXTRA_DATA_AGE, "");
            String ContactGender = extras.getString(EXTRA_DATA_GENDER, "");
            String ContactCity = extras.getString(EXTRA_DATA_CITY, "");
            String ContactCollege = extras.getString(EXTRA_DATA_COLLEGE, "");
            int imageId;
            if(ContactGender.equals("Female")){
                imageId = R.drawable.girl;
            }
            else imageId = R.drawable.girl;

            contacts = new Contacts(ContactName, ContactPhone, ContactEmail, ContactAge, ContactGender, ContactCity, ContactCollege, imageId);

            if(!ContactName.isEmpty()){
                nameTV.setText("Name : " +ContactName);
            }

            if(!ContactPhone.isEmpty()){
                numberTV.setText("Phone : " +ContactPhone);
            }

            if(!ContactAge.isEmpty()){
                ageTV.setText("Age : " +ContactAge);
            }

            if(!ContactGender.isEmpty()){
                genderTV.setText("Gender : " +ContactGender);
            }

            if(!ContactEmail.isEmpty()){
                emailTV.setText("Email : " +ContactEmail);
            }

            if(!ContactCity.isEmpty()){
                cityTV.setText("City : " +ContactCity);
            }

            if(!ContactCollege.isEmpty()){
                collegeTV.setText("College : " +ContactCollege);
            }

            imageView.setImageResource(imageId);
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
