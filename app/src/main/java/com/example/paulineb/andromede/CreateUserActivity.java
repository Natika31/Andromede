package com.example.paulineb.andromede;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CreateUserActivity extends AppCompatActivity {

    static String fileName = "users.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }

    public void goToEvents(View view) throws JSONException, IOException {
        JSONObject users = HandleFiles.read(this, "users.json");

        Log.e("USERS", users.toString());

        TextView comp = (TextView) findViewById (R.id.company);
        String company = comp.getText().toString();

        TextView tit = (TextView) findViewById (R.id.title);
        String title = tit.getText().toString();

        TextView fn = (TextView) findViewById (R.id.firstname);
        String firstname = fn.getText().toString();

        TextView ln = (TextView) findViewById (R.id.lastname);
        String lastname = ln.getText().toString();

        TextView pc = (TextView) findViewById (R.id.postalcode);
        Integer postalcode = Integer.parseInt(pc.getText().toString());

        TextView ci = (TextView) findViewById (R.id.city);
        String city = ci.getText().toString();

        TextView ad = (TextView) findViewById (R.id.address);
        String address = ad.getText().toString();

        TextView em = (TextView) findViewById (R.id.email);
        String email = em.getText().toString();

        User newUser = new User (company, title, firstname, lastname, postalcode, city, address,email, false);

        if (!users.has(newUser.getEmail())) {
            users.put(newUser.getEmail(), newUser.getAllData());
            Log.e("creation", users.toString());
            HandleFiles.create(this, "users.json", users.toString());

            //TODO: envoyer email de confirmation de creation de compte

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            TextView alert = (TextView) findViewById(R.id.alert);
            alert.setText(R.string.error_account_already_exists);
            alert.setTextColor(getResources().getColor(R.color.rougeAlert));

        }

    }



}


