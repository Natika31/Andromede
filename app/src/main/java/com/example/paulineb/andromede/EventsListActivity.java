package com.example.paulineb.andromede;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class EventsListActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        //on récup l'utilisateur courant
        JSONObject currentUser = null;
        try {
            currentUser = HandleFiles.read(this, "currentUser.json");
            Log.e("CURRENT", currentUser.toString());
            //transformer en user
            User user = new User (currentUser);

            if (user.getOrganisor() == true) {
                //si c'est l'organisateur

                //on afficher la possibilite d'annuler l'evt
                Button e1 = (Button) findViewById(R.id.event1);
                Button e2 = (Button) findViewById(R.id.event2);
                e1.setText(R.string.delete);
                e2.setText(R.string.delete);

                //on afficher la possibilite de voir la liste des participants
                TextView part1 = (TextView) findViewById(R.id.participantsE1);
                part1.setVisibility(View.VISIBLE);
                TextView part2 = (TextView) findViewById(R.id.participantsE2);
                part2.setVisibility(View.VISIBLE);

            } else {
                //si c'est un utilisateur
                Button e1 = (Button) findViewById(R.id.event1);
                Button e2 = (Button) findViewById(R.id.event2);
                e1.setText(R.string.subscribe);
                e2.setText(R.string.subscribe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void Subscribe_event1 (View view) throws JSONException {

        Button e1 = (Button) findViewById(R.id.event1);

        if (e1.getText().equals("Annuler")) {
            e1.setText(R.string.deleted);
            //envoyer un mail d'annulation aux participants
        } else {
            //ajouter aux participants
            JSONObject currentUser = HandleFiles.read(this, "currentUser.json");
            Log.e("current???", currentUser.toString());
            User user = new User (currentUser);
            JSONObject participantsE1 = HandleFiles.read(this, "participantsE1.json");
            Log.e("user", user.getAllData().toString());

            if (participantsE1 == null) {
                JSONObject participant = new JSONObject();
                participant.put(user.getEmail(), user.getAllData().toString());
                HandleFiles.create(this, "participantsE1.json", participant.toString());
            } else {
                participantsE1.put(user.getEmail(), currentUser);
                HandleFiles.create(this, "participantsE1.json", participantsE1.toString());
            }
            e1.setText(R.string.subscribed);
            //envoyer un mail d'inscription
        }

    }

    public void Subscribe_event2 (View view) throws JSONException {

        Button e2 = (Button) findViewById(R.id.event2);
        //si c'est l'organisateur
        if (e2.getText().equals("Annuler")) {
            e2.setText(R.string.deleted);
            //envoyer un mail d'annulation aux participants
        } else {
            //si c'est un utilisateur
            e2.setText(R.string.subscribed);
            //ajouter aux participants
            JSONObject currentUser = HandleFiles.read(this, "currentUser.json");
            User user = new User (currentUser);
            JSONObject participantsE2 = HandleFiles.read(this, "participantsE2.json");
            participantsE2.put(user.getEmail(), user.getAllData().toString()); // idem currentUser.ToString()
            HandleFiles.create(this, "participantsE2.json", participantsE2.toString());
            //envoyer un mail d'inscription
        }

    }

    public void goParticipantsE1(View view) throws JSONException {
        Intent intent = new Intent(this, ParticipantsListActivity.class);
        JSONObject participants = HandleFiles.read(this, "participantsE1.json");
        intent.putExtra("Participants", participants.toString());
        startActivity(intent);
    }

    public void goParticipantsE2(View view) throws JSONException {
        Intent intent = new Intent(this, ParticipantsListActivity.class);
        JSONObject participants = HandleFiles.read(this, "participantsE2.json");
        intent.putExtra("Participants", participants.toString());
        startActivity(intent);

    }
}
