package com.example.kelimebilmeoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.kelimebilmeoyunu.databinding.ActivityBitis1Binding;

public class Bitis1 extends AppCompatActivity {

    private ActivityBitis1Binding tasarim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasarim = ActivityBitis1Binding.inflate(getLayoutInflater());
        setContentView(tasarim.getRoot());

        String can = getIntent().getStringExtra("can");
        String puan = getIntent().getStringExtra("puan");

        tasarim.tvC.setText("Kalan Can Sayınız: "+can);
        tasarim.tvP.setText("Kazandığınız Puan: "+puan);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button);
        tasarim.bAS.startAnimation(animation);

        tasarim.bAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bitis1.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}