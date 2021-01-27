package com.green_station_20.problem2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText mobileNumberEt, passwordEt;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileNumberEt = findViewById(R.id.mobileNumberEtId);
        passwordEt = findViewById(R.id.passwordEtId);
        loginBtn = findViewById(R.id.loginBtnId);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonRequest();
            }
        });

    }

    private void JsonRequest() {

        String URL = "https://alhasan.dev/interns/services/member-login.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                /* JSONObject jsonObject = new JSONObject(response);*/
                // Toast.makeText(MainActivity.this, "" + jsonObject, Toast.LENGTH_SHORT).show();
                Log.d("m", "onResponse: " + response);


                Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                intent.putExtra("msg", response.toString());
                startActivity(intent);


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("TAG", "onErrorResponse: " + error);
                Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile", "01234567890");
                params.put("password", "123456");

                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);

    }
}

