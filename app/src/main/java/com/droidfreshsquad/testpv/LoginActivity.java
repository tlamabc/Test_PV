package com.droidfreshsquad.testpv;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {
    private EditText emailEditText, passwordEditText;
//    private Button loginButton; // Changed variable name here
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.buttonLog); // Changed variable name here

        requestQueue = Volley.newRequestQueue(this);

        loginButton.setOnClickListener(new View.OnClickListener() { // Changed variable name here
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                performLogin(email, password);
            }
        });
    }
    private void performLogin(String email, String password) {
        String url = "http://192.168.101.158:8081/testPV/login.php"; // Replace with your server URL

        JSONObject requestData = new JSONObject();
        try {
            requestData.put("email", email);
            requestData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, url, requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = response.getString("result");

                            if (result.equals("ok")) {
                                // Login successful, navigate to ListUserActivity
                                Intent intent = new Intent(LoginActivity.this, ListUserActivity.class);
                                startActivity(intent);
                                finish(); // Close the LoginActivity
                            } else {
                                Toast.makeText(LoginActivity.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "An error occurred.", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        requestQueue.add(loginRequest);
    }



}
