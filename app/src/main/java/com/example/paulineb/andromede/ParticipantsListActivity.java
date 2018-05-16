package com.example.paulineb.andromede;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ParticipantsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);

        Intent intent = getIntent();
        String participants = intent.getStringExtra("Participants");
        try {
            JSONObject listParticipants = new JSONObject(participants);
            String participantsString = "";

            for (Iterator iterator = listParticipants.keys(); iterator.hasNext();) {
                Object cle = iterator.next();
                Object val = listParticipants.get(String.valueOf(cle));
                Log.e("qui es tu ?", val.toString());
                JSONObject participant = new JSONObject(val.toString());
                User user = new User (participant);
                participantsString += user.getLastname() +" "+ user.getFirstname() + "      ,";

            }

            TextView ppp = (TextView) findViewById(R.id.participants);
            ppp.setText(participantsString);

            Log.e("aaa", participantsString);

           /* for () {
                participantsString += participant.get("Nom");
                participantsString += participant.get("Prenom");
            }
            */
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
