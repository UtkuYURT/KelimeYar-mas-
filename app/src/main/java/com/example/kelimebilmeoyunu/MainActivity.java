package com.example.kelimebilmeoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.kelimebilmeoyunu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding tasarim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasarim = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(tasarim.getRoot());

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo);
        tasarim.imageView.startAnimation(animation);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button);
        tasarim.buttonOyunaGiris.startAnimation(animation2);
        tasarim.buttonKurallar.startAnimation(animation2);

        tasarim.buttonKurallar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OyunHakkinda.class);
                startActivity(intent);
            }
        });

        tasarim.buttonOyunaGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, Oyun.class);
                startActivity(intent2);
            }
        });
    }
}