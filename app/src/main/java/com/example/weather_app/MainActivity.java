package com.example.weather_app;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
   EditText ed1;
   Button b1;
   TextView t1,h1,p1;
    String api_url= "https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
//    String apikey="ec90c1cf27f5fb7d43e9e95047d3f00a";
    String apikey = "8e7ef48916ed1b3b467430cd6d2f058f";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       b1=(Button)findViewById(R.id.b1);
        ed1=(EditText)findViewById(R.id.ed1);
        t1=(TextView) findViewById(R.id.t1);
        p1=(TextView) findViewById(R.id.p1);
        h1=(TextView) findViewById(R.id.h1);
    }
    public void fetch(View v){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi myapi=retrofit.create(weatherapi.class);

        Call<Example> examplecall=myapi.fetch(ed1.getText().toString().trim(),apikey);
        examplecall.enqueue(new Callback<Example>() {
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                 if(response.code()==404){
                     Toast.makeText(MainActivity.this,"please enter a valid city...",Toast.LENGTH_LONG).show();
                 }
                 else if(!(response.isSuccessful())){
                     Toast.makeText(MainActivity.this,response.code()+" ",Toast.LENGTH_LONG).show();
                     Log.d("Res", "onResponse:" + response.code() + " ");
                 }
                Log.d("Response", "onResponse: " + response.body().toString());
                 Example mydata=response.body();

                Main main = mydata.getMain();
                Double temp=main.getTemp();
                 Integer tempreture=(int)(temp-273.15);
                 t1.setText("Temp " + String.valueOf(tempreture)+ "° C");
                 h1.setText("Humidity "+response.body().getMain().getHumidity()+"%");
                 p1.setText("Pressure "+response.body().getMain().getPressure()+ "mbar");

            }
            
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"In Start",Toast. LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"In Stop",Toast. LENGTH_SHORT).show();
    }
//restart thay activity etle badhunikdi jayok
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"In Destroy",Toast. LENGTH_SHORT).show();
    }//aaj ne?

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"In Pause",Toast. LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"In Resume",Toast. LENGTH_SHORT).show();
    }
}