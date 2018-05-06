package com.example.paulineb.andromede;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Create_User_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }

    public void goToEvents(View view) {
        Intent intent = new Intent(this, Events_List_activity.class);
        startActivity(intent);
    }

//renvoyer vers la vue liste d'évts

}


