package com.example.booksie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class WeatherActivity extends AppCompatActivity {
    EditText enCity;
    TextView result;
    //private final String url="https://api.openweathermap.org/data/2.5/weather?q=london&appid=a38764f14f8685f53e4519e3954f8d19";
    //private final String appid="a38764f14f8685f53e4519e3954f8d19";
    //DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
       // enCountry=findViewById(R.id.enCountry);
        enCity=findViewById(R.id.enCity);
        result= findViewById(R.id.result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void get(View v) {
        // String tempUrl ="";
        //String apikey = "a38764f14f8685f53e4519e3954f8d19";
        String city = enCity.getText().toString();
        //String country = enCountry.getText().toString().trim();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=a38764f14f8685f53e4519e3954f8d19";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object= response.getJSONObject("main");
                    String temperature=object.getString("temp");
                    Double temp = Double.parseDouble(temperature) - 273.15;
                    result.setText("The Temperature is "+temp.toString().substring(0, 5)+ " C");

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WeatherActivity.this, "Please Try Again.", Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(request);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}