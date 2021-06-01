package com.mirea.grachev.room;
import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static App instance;
    private MainActivity.AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, MainActivity.AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public MainActivity.AppDatabase getDatabase() {
        return database;
    }
}