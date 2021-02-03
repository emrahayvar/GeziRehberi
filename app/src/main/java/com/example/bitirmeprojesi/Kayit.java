package com.example.bitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Kayit extends AppCompatActivity {

    EditText mAdSoyad,mEmail,mSifre,mTelefon;
    Button mKayitBtn;
    TextView mGirisBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        KayitButonDinleyicisi();
        girisSayfasinaGidis();


    }

    // kullanıcıyı kaydetme butonuna basılınca olacaklar
    public void KayitButonDinleyicisi() {

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        mAdSoyad = findViewById(R.id.adSoyad);
        mEmail = findViewById(R.id.email);
        mSifre = findViewById(R.id.sifre);
        mTelefon = findViewById(R.id.telefon);
        mKayitBtn = findViewById(R.id.kayitButon);

        mKayitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mSifre.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("E-mail alanı boş bırakılamaz!");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mSifre.setError("Şifre alanı boş bırakılamaz!");
                }



                if(password.length() < 6) {
                    mSifre.setError("Şifre 5 karakterden uzun olmalı!");
                }

                progressBar.setVisibility(View.VISIBLE);
//kullanıcıyı firebase e kaydetme

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Kayit.this,"Kullanıcı hesabı oluşturuldu.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Kayit.this,"Hata!" , Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });

    }

    //Giris Sayfasına yönlendirme textview i
    public void girisSayfasinaGidis() {
        mGirisBtn = findViewById(R.id.olusturmaText);
        mGirisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Giris.class));
            }
        });
    }

}