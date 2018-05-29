package com.example.paulineb.andromede;
import android.content.Intent;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialisation
        JSONObject participantsE2 = null;
        JSONObject participantsE1 = null;
        try {
            participantsE2 = HandleFiles.read(this, "participantsE2.json");
            participantsE1 = HandleFiles.read(this, "participantsE1.json");
            if (participantsE1 == null) {
                HandleFiles.create(this, "participantsE1.json", new JSONObject("").toString());
            }
            if (participantsE2 == null) {
                HandleFiles.create(this, "participantsE2.json", new JSONObject("").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        boolean isFilePresent = HandleFiles.isFilePresent(this, "users.json");

        if(!isFilePresent) {
            JSONObject orga = new JSONObject();
            JSONObject jeandupont = new JSONObject();
            try {
                jeandupont.put("company", "Airbus");
                jeandupont.put("title", "Mr");
                jeandupont.put("firstname", "Jean");
                jeandupont.put("lastname", "Dupont");
                jeandupont.put("address", "Route de Narbonne");
                jeandupont.put("cp", 31000);
                jeandupont.put("city", "Toulouse");
                jeandupont.put("email","jd.eventconnect@gmail.com");
                jeandupont.put("organisor", true);

                orga.put("jd.eventconnect@gmail.com", jeandupont);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            boolean isFileCreated = HandleFiles.create(this, "users.json", orga.toString());
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    public void newAccount(View view) {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
    }

    public void connect(View view) throws JSONException {
        JSONObject users = HandleFiles.read(this, "users.json");
        Log.e("USERS", users.toString());

        // Récupérer l'adresse mail de la vue
        TextView em = (TextView) findViewById (R.id.email);
        String email = em.getText().toString();

        if (users.has(email)) {
            Log.e("CONNECT", "OK");
            //on sauvegarde l'utilisateur courant
            String currentUser = users.get(email).toString();
            HandleFiles.create(this,"currentUser.json", currentUser);

            Intent intent = new Intent(this, EventsListActivity.class);
            startActivity(intent);

        } else {
            //Afficher champ erreur
            Log.e("CONNECT", "KO");
            TextView error = (TextView) findViewById(R.id.error_connect);
            error.setText(R.string.error_account_doesnt_exists);
            error.setVisibility(View.VISIBLE);
        }
    }


}

