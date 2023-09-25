package com.example.logbook3lan2;

import com.cloudinary.android.MediaManager;

import java.util.HashMap;
import java.util.Map;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dydd7mqle");
        config.put("api_key", "446888246178164");
        config.put("api_secret", "HcT6rhBOXzGh-pm1Agd8Q34rask");
        MediaManager.init(this, config);

    }
}