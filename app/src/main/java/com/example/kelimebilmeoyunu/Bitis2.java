package com.example.kelimebilmeoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.kelimebilmeoyunu.databinding.ActivityBitis2Binding;

public class Bitis2 extends AppCompatActivity {

    private ActivityBitis2Binding tasarim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasarim = ActivityBitis2Binding.inflate(getLayoutInflater());
        setContentView(tasarim.getRoot());

        String puan = getIntent().getStringExtra("puan");
        tasarim.textViewP.setText("Kazandığınız Puan: " + puan);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button);
        tasarim.buttonAnaSayfa.startAnimation(animation);

        tasarim.buttonAnaSayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bitis2.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}