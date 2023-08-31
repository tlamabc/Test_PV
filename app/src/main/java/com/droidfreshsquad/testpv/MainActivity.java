package com.droidfreshsquad.testpv;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import com.droidfreshsquad.testpv.SignUpActivity;
import com.droidfreshsquad.testpv.LoginActivity;
import com.droidfreshsquad.testpv.ListUserActivity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openSU(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void openLoginActivity (View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openUserListActivity(View view) {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }
}