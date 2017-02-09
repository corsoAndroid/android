package com.example.genji.am101_mongo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EmbeddedService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "call ApiUtils.getEmbeddedService()");
        mService = ApiUtils.getEmbeddedService();



        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "call loadCars()");
                loadCars();
            }
        });


    }

    public void loadCars() {
        mService.getAnswers().enqueue(new Callback<Embedded>() {

            private ArrayList<Car> cars = new ArrayList<>();

            @Override
            public void onResponse(Call<Embedded> call, Response<Embedded> response) {

                if(response.isSuccessful()) {
                    Log.d("MainActivity", "posts loaded from API");
                    cars = (ArrayList<Car>)response.body().getEmbedded();
                    MainActivity.this.showCars(cars);

                } else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d("MainActivity", "handle request errors");
                }
            }

            @Override
            public void onFailure(Call<Embedded> call, Throwable t) {
                showErrorMessage();
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    void showCars(ArrayList<Car> cars){

        TextView carList = (TextView) findViewById(R.id.carList);
        String list ="";
        for(Car car : cars){
            list += "name: " + car.getName() +", price: " + car.getPrice() + "\n";
        }
        carList.setText(list);

    }

    void showErrorMessage(){
        Log.d("MainActivity", "Error");
    }
}
