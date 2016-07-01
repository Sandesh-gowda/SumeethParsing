package com.creator.dataparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.creator.dataparsing.model.Menu;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String responseData;
    public String URLMENU = "http://appshoppee.in/americanbyte/API/today_sp_mnu.php";
    public String urlM = "http://appshoppee.in/americanbyte/API/menus_list.php";
    public String urlD = "http://appshoppee.in/americanbyte/API/deset_mnu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

makeSearchCall();







    }


    private void makeSearchCall() {
       StringRequest jsonObjRequest = new StringRequest(Request.Method.GET,
               URLMENU,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        responseData = response;
                        setData(response);
                        VolleyLog.d("volley", "Error: " + response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();

                Log.d("MenuFragment", "onErrorResponse : 103");
            }
        });
        Controller.getInstance().getRequestQueue().add(jsonObjRequest);

    }







    private void setData(String response) {
        try {
            Type listOfTestObject = new TypeToken<List<Menu>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<Menu> Menulist = gson.fromJson(response,listOfTestObject);
            if (Menulist == null || Menulist.size() == 0) {
              //TODO
                /*
                * check whether it has some data or not ?
                *
                *
                * */



            } else {
                /*TODO:set the adapter of recyclerview .
                *
                *
                * */
               // havingSomeStock();
             //   adapter = new StockAdapter(SearchResultActivity.this, carsList, false, false, false);
             //   recyclerView.setAdapter(adapter);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

}