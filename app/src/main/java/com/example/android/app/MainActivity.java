package com.example.android.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class MainActivity extends AppCompatActivity {

    Button btn_send;
    EditText textName;
    EditText textPrice;
    EditText textQuantity;

    RequestQueue mRequestQueue; // очередь запросов

    private static final String testUrl = "https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22";
    String task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = (EditText) findViewById(R.id.textName);
        textPrice = (EditText) findViewById(R.id.textPrice);
        textQuantity = (EditText) findViewById(R.id.textQuantity);
        btn_send = (Button) findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(testUrl);
                textName.setText(task);
            }
        });
    }

    private void getData(String url) {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, //GET - API-запрос для получение данных
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // получаем JSON-обьекты main
                    // (в фигурных скобках - объекты, в квадратных - массивы (JSONArray).
                    /*
                    JSONObject GlobalObject = new JSONObject(response.toString());
                    JSONArray array = GlobalObject.getJSONArray("myproducts");

                    for(int countItem = 0; countItem < array.length(); countItem++)
                    {
                        JSONObject obj = array.getJSONObject(countItem);
                        task = obj.getString("name");
                    }

                     */

                    JSONObject obj = new JSONObject(response.toString());
                    task = obj.getString("base");

                    // task = content.getString("content");
                    // присваеваем переменным соответствующие значения из API
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() { // в случае возникновеня ошибки
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request); // добавляем запрос в очередь
    }

}