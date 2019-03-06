package com.app.fitme.fitme.Models;

import com.google.firebase.database.FirebaseDatabase;

public class FitMeApp extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable persistence mode to use in the application later
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

}
