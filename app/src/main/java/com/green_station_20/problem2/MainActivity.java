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

import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

        String mobileNumber = mobileNumberEt.getText().toString();
        String password = passwordEt.getText().toString();

        mobileNumberEt.setText("");
        passwordEt.setText("");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                //   Log.d("m", "onResponse: "+response);
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
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                Params.put("mobile", mobileNumber);
                Params.put("password", password);
                return Params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);

    }
}

