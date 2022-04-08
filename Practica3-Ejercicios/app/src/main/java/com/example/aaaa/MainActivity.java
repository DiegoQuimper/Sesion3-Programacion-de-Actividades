package com.example.aaaa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aaaa.databinding.ActivityMainBinding;

public class  MainActivity extends AppCompatActivity   {

    protected static final int TIMER_RUNTIME = 10000;
    protected boolean nbActivo;
    protected ProgressBar nProgressBar;
    protected TextView textViewa;
    String eti = "CicloDeVida";


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(eti,"En el evento onCreate()");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.example.SegundaActividad"));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }
    public void two(View two) {
        nProgressBar = (ProgressBar)findViewById(R.id.progressBar2);
        textViewa =(TextView)findViewById(R.id.textView2);
        final Thread timerThread = new Thread(){
            @Override
            public void run(){
                nbActivo = true;
                try{
                    int espera1 = 0;
                    while(nbActivo && (espera1 < TIMER_RUNTIME)){
                        sleep(200);
                        if(nbActivo){
                            espera1 += 200;
                            actualizarProgress(espera1);
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    textViewa.setText("Cargando....");
                                    textViewa.setVisibility(View.VISIBLE);
                                }
                            });

                        }
                    }
                } catch(InterruptedException e){
                } finally{
                    onContinuar2();
                }
            }
        };
        timerThread.start();

    }

    public void one(View one) {
        nProgressBar = (ProgressBar)findViewById(R.id.progressBar2);
        textViewa =(TextView)findViewById(R.id.textView2);
        final Thread timerThread = new Thread(){
            @Override
            public void run(){
                nbActivo = true;
                try{
                    int espera1 = 0;
                    while(nbActivo && (espera1 < TIMER_RUNTIME)){
                        sleep(200);
                        if(nbActivo){
                            espera1 += 200;
                            actualizarProgress(espera1);
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    textViewa.setText("Cargando....");
                                    textViewa.setVisibility(View.VISIBLE);
                                }
                            });

                        }
                    }
                } catch(InterruptedException e){
                } finally{
                    onContinuar();
                }
            }
        };
        timerThread.start();

    }

    public void actualizarProgress(final int timePassed){
        if(null != nProgressBar){
            final int progress = nProgressBar.getMax() * timePassed
                    /TIMER_RUNTIME;
            nProgressBar.setProgress(progress);
        }
    }
    public void onContinuar(){
        Log.d("mensajeFinal", "Su barra de progreso acaba de cargar");
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                textViewa.setText("Completado!");

            }
        });
        startActivity(new Intent("com.example.SegundaActividad"));
    }

    public void onContinuar2(){
        Log.d("mensajeFinal", "Su barra de progreso acaba de cargar");
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                textViewa.setText("Completado!");

            }
        });
        startActivity(new Intent("com.example.TerceraActividad"));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void onStart()
    {
        super.onStart();
        Log.d(eti,"En el evento onStart()");
    }
    public void onRestart()
    {
        super.onRestart();
        Log.d(eti,"En el evento onRestart()");
    }
    public void onResume()
    {
        super.onResume();
        Log.d(eti,"En el evento onResume()");
    }
    public void onPause()
    {
        super.onPause();
        Log.d(eti,"En el evento onPause()");
    }
    public void onStop()
    {
        super.onStop();
        Log.d(eti,"En el evento onStop()");
    }
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(eti,"En el evento onDestroy()");
    }


}