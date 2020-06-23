package com.example.contactsapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contacts")
public class Contacts {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Name")
    private String Name;

    @ColumnInfo(name = "Phone Number")
    private String Number;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "Age")
    private String Age;

    @ColumnInfo(name = "Gender")
    private String Gender;

    @ColumnInfo(name = "City")
    private String City;

    @ColumnInfo(name = "College")
    private String College;

    public Contacts(@NonNull String name, String number, String email, String age, String gender, String city, String college) {
        this.Name = name;
        this.Number = number;
        this.email = email;
        this.Age = age;
        this.Gender = gender;
        this.City = city;
        this.College = college;
    }

    public Contacts(){

    }

    @NonNull
    public String getName() {
        return Name;
    }

    public void setName(@NonNull String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }

    public boolean isContactEqual(Contacts contacts){
        return ((Name==contacts.Name) && (Number==contacts.Number) && (email==contacts.email) && (Age==contacts.Age) && (Gender==contacts.Gender) && (City==contacts.City) && (College==contacts.College));
    }
}


