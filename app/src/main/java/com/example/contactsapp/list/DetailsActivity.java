package com.example.contactsapp.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactsapp.R;
import com.example.contactsapp.addData.AddActivity;
import com.example.contactsapp.data.Contacts;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.security.Permissions;
import java.security.acl.Permission;

public class DetailsActivity extends AppCompatActivity {
    ActionBar actionBar;
    TextView nameTV, numberTV, ageTV, emailTV, cityTV, collegeTV, genderTV;
    ImageView imageView;
    Contacts contact;
    int imageId;
    String ContactPhone, ContactAge, ContactGender, ContactEmail, ContactCity, ContactCollege, ContactName;
    long contactId;
    private final static int NEW_DATA_REQUEST_CODE = 1;
    private final static int UPDATE_DATA_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_NAME = "extra_contact_name";
    public static final String EXTRA_DATA_PHONE = "extra_contact_phone";
    public static final String EXTRA_DATA_EMAIL = "extra_contact_email";
    public static final String EXTRA_DATA_AGE = "extra_contact_age";
    public static final String EXTRA_DATA_GENDER = "extra_contact_gender";
    public static final String EXTRA_DATA_CITY = "extra_contact_city";
    public static final String EXTRA_DATA_COLLEGE = "extra_contact_college";
    public static final String EXTRA_DATA_ID = "extra_data_id";

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
//                    requestPermissions(, 1);
                    return;
                }
                startActivity(intent);
            }
        });


        actionBar = getSupportActionBar();
        final ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#F1F1F1"));
        actionBar.setBackgroundDrawable(colorDrawable);

        final Bundle extras = getIntent().getExtras();

        if (extras!=null) {

                             ContactName = extras.getString(EXTRA_DATA_NAME, "");
                             ContactPhone =extras.getString(EXTRA_DATA_PHONE, "");
                             ContactEmail = extras.getString(EXTRA_DATA_EMAIL, "");
                             ContactAge = extras.getString(EXTRA_DATA_AGE, "");
                             ContactGender =  extras.getString(EXTRA_DATA_GENDER, "");
                             ContactCity = extras.getString(EXTRA_DATA_CITY, "");
                             ContactCollege = extras.getString(EXTRA_DATA_COLLEGE, "");
                             contactId = extras.getLong(EXTRA_DATA_ID, 0L);
                        if(ContactGender.equals("Female")){
                        imageId = R.drawable.girl;
                    }
                    else imageId = R.drawable.girl;

                    contact = new Contacts(ContactName, ContactPhone, ContactEmail, ContactAge, ContactGender, ContactCity, ContactCollege, imageId, contactId );

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
                intent.putExtra(EXTRA_DATA_ID, contact.getId());
                intent.putExtra(EXTRA_DATA_NAME, contact.getName());
                intent.putExtra(EXTRA_DATA_PHONE,contact.getNumber());
                intent.putExtra(EXTRA_DATA_EMAIL,contact.getEmail());
                intent.putExtra(EXTRA_DATA_AGE,contact.getAge());
                intent.putExtra(EXTRA_DATA_GENDER,contact.getGender());
                intent.putExtra(EXTRA_DATA_CITY,contact.getCity());
                intent.putExtra(EXTRA_DATA_COLLEGE,contact.getCollege());

                startActivityForResult(intent, UPDATE_DATA_REQUEST_CODE);
                return true;


            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT,contact.getName() +"\n"+ contact.getNumber()
                + "\n" + contact.getAge() + "\n" + contact.getGender() +"\n" + contact.getEmail() + "\n"
                + contact.getCollege() + "\n" + contact.getCity());

                startActivity(Intent.createChooser(sharingIntent, "contact Details"));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
