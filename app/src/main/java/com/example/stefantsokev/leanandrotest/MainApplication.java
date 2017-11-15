package com.example.stefantsokev.leanandrotest;

import android.app.Application;
import android.widget.EditText;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.leanplum.Leanplum;

// For tracking user sessions.
import com.leanplum.LeanplumActivityHelper;
// For push notifications.
import com.leanplum.LeanplumPushService;

import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.uieditor.internal.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Stefan.Tsokev on 11/15/2017.
 */

public class MainApplication extends Application {

    @Variable
    public static String str1 = "Welcome to My App! Please enter your name below in order to be forwarded to our members area.";


    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        //  For session lifecyle tracking.
        Parser.parseVariablesForClasses(MainActivity.class, DisplayMessageActivity.class);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);
        Leanplum.enableVerboseLoggingInDevelopmentMode();

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("Test", str1);
            }
        });

        // Insert your API keys here.
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("app_G9hMPfWnShGsr9NIk462J0GLjq2uxNsSiMRaO4GhTk8", "dev_vRLZENiYIxYuVSaKjJcvWJxRWGNQAmMXbwQ3xWfiMRk");
        } else {
            Leanplum.setAppIdForProductionMode("app_G9hMPfWnShGsr9NIk462J0GLjq2uxNsSiMRaO4GhTk8", "prod_7gwzfMULOkCNMAJfBraPaxqJxD8yAvkNmsLK1J8kRGg");
        }

        // Optional: Tracks all screens in your app as states in Leanplum.
        Leanplum.trackAllAppScreens();

        // Enable push notifications.

        // Option 2: Firebase Cloud Messaging
        // Be sure to upload your Server API key to our dashboard.
        //LeanplumPushService.enableFirebase();

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        // This will only run once per session, even if the activity is restarted.
        Leanplum.start(this);
        Leanplum.track("DisplayMessageActivity");
    }
}
