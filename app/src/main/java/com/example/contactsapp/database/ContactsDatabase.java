package com.example.contactsapp.database;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.contactsapp.data.Contacts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contacts.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactsDao contactsDao();
    public static ContactsDatabase INSTANCE = null;
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static ContactsDatabase getInstance(final Context context){
        if(INSTANCE==null){
            synchronized (ContactsDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactsDatabase.class,
                            "ContactsDatabase")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    executorService.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            prePopulateDb(context.getAssets(), INSTANCE.contactsDao());
                                        }
                                    });
                                }
                            })
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

    private static void prePopulateDb(AssetManager assetManager, ContactsDao contactsDao){
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String json = "";
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(assetManager.open("contactData.json"))
            );
            String mLine;
            while ((mLine = bufferedReader.readLine()) != null){
                stringBuilder.append(mLine);
            }
            json = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try{
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("Name");
                String phone = jsonObject.getString("Phone");
                String email = jsonObject.getString("Email");
                String age = jsonObject.getString("Age");
                String gender = jsonObject.getString("Gender");
                String city = jsonObject.getString("City");
                String college = jsonObject.getString("College");

                contactsDao.insertContact(new Contacts(name, phone, email, age, gender, city, college));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
