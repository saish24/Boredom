package com.example.bored;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView action;
    private TextView type;
    private RatingBar price;
    private RatingBar accessibility;
    private TextView Refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        action = (TextView) findViewById(R.id.tv_action);
        type = (TextView) findViewById(R.id.tv_typeact);
        price = (RatingBar) findViewById(R.id.ratingBar1);
        accessibility = (RatingBar) findViewById(R.id.ratingBar2);
        Refresh = (TextView) findViewById(R.id.bt_refresh);

        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Loading ...", Toast.LENGTH_SHORT).show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.boredapi.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                BoredApiInstance boredApiInstance = retrofit.create(BoredApiInstance.class);

                Call call =  boredApiInstance.getmesomething();

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        LinearLayout layout1 = (LinearLayout) findViewById(R.id.row1);
                        layout1.setVisibility(View.VISIBLE);
                        LinearLayout layout2 = (LinearLayout) findViewById(R.id.row2);
                        layout2.setVisibility(View.VISIBLE);
                        LinearLayout layout3 = (LinearLayout) findViewById(R.id.row3);
                        layout3.setVisibility(View.VISIBLE);

                        if(!response.isSuccessful()){
                            action.setText(response.code());
                            return;
                        }
                        BorAct borAct = (BorAct) response.body();
                        action.setText(borAct.getAct());
                        String str = borAct.getType();
                        str = str.substring(0,1).toUpperCase() + str.substring(1);
                        type.setText(str);
                        float stars = borAct.getPrice();
                        price.setRating(5*stars);
                        stars = borAct.getAccessibility();
                        accessibility.setRating(5*stars);

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        String str = t.getMessage();
                        action.setText(str);
                        LinearLayout layout1 = (LinearLayout) findViewById(R.id.row1);
                        layout1.setVisibility(View.GONE);
                        LinearLayout layout2 = (LinearLayout) findViewById(R.id.row2);
                        layout2.setVisibility(View.GONE);
                        LinearLayout layout3 = (LinearLayout) findViewById(R.id.row3);
                        layout3.setVisibility(View.GONE);
                    }
                });
            }
        });

    }
}