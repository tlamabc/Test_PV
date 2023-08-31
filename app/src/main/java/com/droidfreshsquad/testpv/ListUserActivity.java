package com.droidfreshsquad.testpv;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class ListUserActivity extends AppCompatActivity {
    private ListView userListView;
    private RequestQueue requestQueue;
    private static final String LIST_USERS_URL = "http://192.168.101.158:8081/testPV/listuser.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userListView = findViewById(R.id.userListView);
        requestQueue = Volley.newRequestQueue(this);

        loadUserList();
    }

    private void loadUserList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LIST_USERS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            List<String> userList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                userList.add(jsonArray.getJSONObject(i).getString("email"));
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(ListUserActivity.this,
                                    android.R.layout.simple_list_item_1, userList);

                            userListView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListUserActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest);
    }
}
