package com.example.kelimebilmeoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.kelimebilmeoyunu.databinding.ActivityMainBinding;
import com.example.kelimebilmeoyunu.databinding.ActivityOyunBinding;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Oyun extends AppCompatActivity {

    private ActivityOyunBinding tasarim;
    private int dogruCevap = 0;
    private int can = 5;
    private int puan = 0;
    private int sayac = 0;
    String[] soru = new String[10];
    String[] cevap = new String[10];
    int randomSayilar[] = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasarim = ActivityOyunBinding.inflate(getLayoutInflater());
        setContentView(tasarim.getRoot());

        tasarim.textViewPuan.setText("Puanınız: "+ puan);
        String c = Integer.toString(can);
        tasarim.textViewKalanCan.setText(c);

        soru[0] = ("Tropikal iklimde yaşayan otçul, egzotik bir kertenkele türü");
        soru[1] = ("Kıtaları birbirinden ayıran engin, açık denizler.");
        soru[2] = ("Mal ve hizmetlerin değişim aracı olarak tanımlanan kağıt ya da maden");
        soru[3] = ("Halk dilinde çok konuşan anlamına gelen kelime");
        soru[4] = ("Bir yere çıkmak veya bir yerden inmeye yarayan basamaklar dizisi");
        soru[5] = ("Karşısındakini yanıltarak veya yıldırarak bir işten caydırmak için söylenen asılsız söz veya takınılan aldatıcı tavır");
        soru[6] = ("Japon kökenli bir köpek cinsi");
        soru[7] = ("Hayvan hastalıkları hekimi, baytar");
        soru[8] = ("Yumuşak çelikten yapılmış sac");
        soru[9] = ("Gereğinden çok yemek yiyen, doymak bilmeyen kimse");

        cevap[0] = ("iguana");
        cevap[1] = ("okyanus");
        cevap[2] = ("para");
        cevap[3] = ("geveze");
        cevap[4] = ("merdiven");
        cevap[5] = ("blöf");
        cevap[6] = ("akita");
        cevap[7] = ("veteriner");
        cevap[8] = ("teneke");
        cevap[9] = ("obur");

        randomSayiUretme();
        soruUret();
        tasarim.buttonDogru.setEnabled(false);

        tasarim.buttonDogru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = soruyuBulma();
                tasarim.editTextCevap.setText(cevap[r]);
                tasarim.buttonTahmin.setEnabled(false);
            }
        });

        tasarim.buttonTahmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = soruyuBulma();
                String tahmin= tasarim.editTextCevap.getText().toString();
                tahmin = tahmin.trim();
                if (Objects.equals(tahmin, cevap[r])){
                    Toast toast = Toast.makeText(getApplicationContext(), "Tebrikler Doğru Cevap", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    tasarim.buttonDogru.setEnabled(false);
                    puan++;
                    tasarim.textViewPuan.setText("Puanınız: "+ puan);
                    tasarim.buttonTahmin.setEnabled(false);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Maalesef Yanlış Cevap", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    tasarim.buttonTahmin.setEnabled(false);
                    tasarim.buttonDogru.setEnabled(true);
                    can();
                }
            }
        });

        tasarim.buttonSonrakiSoru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sayac < 9){
                    sayac++;
                    tasarim.buttonTahmin.setEnabled(true);
                    tasarim.buttonDogru.setEnabled(false);
                    tasarim.editTextCevap.setText("");
                    soruUret();
                }
                else if (sayac == 9){
                    bitir();
                }
            }
        });
    }

    public void randomSayiUretme(){
        int sayi;
        randomSayilar[0] = 0;
        for (int i = 1; i < 10; i++) {
            sayi = (int) (Math.random() * 10);
            for (int j = 0; j <= i; j++) {
                if (randomSayilar[j] == sayi) {
                    sayi = (int) (Math.random() * 10);
                    j=0;
                }
            }
            randomSayilar[i] = sayi;
        }
    }

    public void soruUret() {
        animation();
        int s = 0;
        if (sayac < 10) {
           s = randomSayilar[sayac];
           tasarim.textViewSoru.setText(soru[s]);
        }
    }

    public void can() {
        can--;
        String c = Integer.toString(can);
        if (can > 0) {
            tasarim.textViewKalanCan.setText(c);
        }
        if (can==0){
            Intent intent = new Intent(Oyun.this, Bitis2.class);
            String p = Integer.toString(puan);
            intent.putExtra("puan", p);
            finish();
            startActivity(intent);
        }
    }

    public int soruyuBulma(){
        int r = 0;
        String sorulan = tasarim.textViewSoru.getText().toString();
        for (int i=0; i<10; i++){
            if (soru[i].equals(sorulan)){
                r = i;
            }
        }
        return r;
    }

    public void bitir(){
        String c = Integer.toString(can);
        String p = Integer.toString(puan);
        Toast toast = Toast.makeText(getApplicationContext(), "Şimdilik Soru Sayımız Bu Kadar Sizi Bitiş Sayfasına Yönlendiriyoruz...", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        Intent intent2 = new Intent(Oyun.this, Bitis1.class);
        intent2.putExtra("can", c);
        intent2.putExtra("puan", p);
        finish();
        startActivity(intent2);
    }

    public void animation(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.textview);
        tasarim.textViewSoru.startAnimation(animation);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button2);
        tasarim.buttonDogru.startAnimation(animation2);
        tasarim.buttonSonrakiSoru.startAnimation(animation2);
        tasarim.buttonTahmin.startAnimation(animation2);
    }

}