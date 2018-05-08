package com.example.paulineb.andromede;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;


public class EventsListActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        //on récup l'utilisateur courant
        String currentUser = HandleFiles.read(this, "currentUser.json");
        try {
            JSONObject user = new JSONObject(currentUser);
            Log.e("CURRENT", currentUser.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (currentUser.contains("jeandupont@gmail.com")) {
            //si c'est l'organisateur
            Button e1 = (Button) findViewById(R.id.event1);
            Button e2 = (Button) findViewById(R.id.event2);
            e1.setText(R.string.delete);
            e2.setText(R.string.delete);

        } else {
            //si c'est un utilisateur
            Button e1 = (Button) findViewById(R.id.event1);
            Button e2 = (Button) findViewById(R.id.event2);
            e1.setText(R.string.subscribe);
            e2.setText(R.string.subscribe);
        }

        boolean isFilePresent = HandleFiles.isFilePresent(this, "participants.json");

        if(isFilePresent) {
            String jsonString = HandleFiles.read(this, "participants.json");
        } else {
            JSONObject participants = new JSONObject();
            JSONObject event1 = new JSONObject();
            JSONObject event2 = new JSONObject();
            try {
                participants.put("event1", event1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                participants.put("event1", event2);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            boolean isFileCreated = HandleFiles.create(this, "participants.json", participants.toString());
        }
    }


    public void Subscribe_event1 (View view) {

        //si c'est l'organisateur
        Button e1 = (Button) findViewById(R.id.event1);
        e1.setText(R.string.deleted);
            //envoyer un mail d'annulation aux participants

        //si c'est un utilisateur
        e1.setText(R.string.subscribed);
            //envoyer un mail d'inscription
            //ajouter aux participants
        //participants.put(evt1 - email)



    }

    public void Subscribe_event2 (View view) {

        //si c'est l'organisateur
        Button e2 = (Button) findViewById(R.id.event2);
        e2.setText(R.string.deleted);
           //envoyer un mail d'annulation aux participants

        //si c'est un utilisateur
        e2.setText(R.string.subscribed);
            //envoyer un mail d'inscription
            //ajouter aux participants

        //participants.put(evt2 - email)
    }



}
