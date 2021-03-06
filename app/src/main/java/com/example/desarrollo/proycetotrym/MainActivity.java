package com.example.desarrollo.proycetotrym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_DESCOUNT = "descount_key";
    private Button buttonSubscribeteAndroid;
    private Button buttonSubscribeteFirebase;

    private TextView textDescountMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDescountMessage = (TextView) findViewById(R.id.TextDescountMessage);
        buttonSubscribeteAndroid = (Button) findViewById(R.id.buttonSubscribeteAndroid);
        buttonSubscribeteFirebase = (Button) findViewById(R.id.buttonSubscribeteFirebase);
        textDescountMessage.setVisibility(View.GONE);

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "token "+token);

        if(getIntent().getExtras() != null){
            textDescountMessage.setVisibility(View.VISIBLE);
            String descount = getIntent().getExtras().getString(KEY_DESCOUNT);
            textDescountMessage.append(descount);
        }
    }

    public void suscribeAndroid(View view){

        FirebaseMessaging.getInstance().subscribeToTopic("Android");
        Toast.makeText(this,"Felicidades te suscribiste a Android",Toast.LENGTH_SHORT).show();

    }

    public void suscribeFirebase(View view){

        FirebaseMessaging.getInstance().subscribeToTopic("Firebase");
        Toast.makeText(this,"Felicidades te suscribiste a Firebase",Toast.LENGTH_SHORT).show();

    }
    public void unSuscribeAndroid(View view){

        FirebaseMessaging.getInstance().unsubscribeFromTopic("Android");
        Toast.makeText(this,"Acabas de cancelar tu subscrición a Android",Toast.LENGTH_SHORT).show();

    }

    public void unSuscribeFirebase(View view){

        FirebaseMessaging.getInstance().unsubscribeFromTopic("Firebase");
        Toast.makeText(this,"Acabas de cancelar tu subscrición a Android",Toast.LENGTH_SHORT).show();

    }

}
