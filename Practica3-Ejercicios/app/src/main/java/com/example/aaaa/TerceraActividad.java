package com.example.aaaa;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaaa.Retrofit.RetrofitInterface;
import com.example.aaaa.Retrofit.Retrofitbuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class TerceraActividad extends AppCompatActivity {
    Button button;
    EditText divisaaserconvertida;
    EditText divisaconvertida;
    Spinner convertir1;
    Spinner convertir2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_actividad);
        //asignando ids a las variables
        divisaconvertida = (EditText) findViewById(R.id.divisa_convertida);
        divisaaserconvertida = (EditText) findViewById(R.id.divisa_a_ser_convertida);
        convertir1 = (Spinner) findViewById(R.id.convertir_a);
        convertir2 = (Spinner) findViewById(R.id.convertir_de);

        //funcionalidad
        String[] dropDownList = {"USD", "PEN", "EUR", "GBP", "INR", "BRL", "MXN", "CNY", "JPY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dropDownList);
        convertir1.setAdapter(adapter);
        convertir2.setAdapter(adapter);


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitInterface retrofitInterface = Retrofitbuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertir2.getSelectedItem().toString());

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(divisaaserconvertida.getText().toString());
                        double multiplier = Double.valueOf(rates.get(convertir1.getSelectedItem().toString()).toString());
                        double result = currency * multiplier;
                        divisaconvertida.setText(String.valueOf(result));
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }


                });


            }
        });


    }
}