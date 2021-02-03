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

public class Giris extends AppCompatActivity {

    EditText mEmail,mSifre;
    Button mGirisBtn;
    TextView mHesapOlusturmaBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        girisButonDinleyicisi();
        girisSayfasinaGidisButonu();

    }

    public void girisButonDinleyicisi() {

        mEmail = findViewById(R.id.email);
        mSifre = findViewById(R.id.sifre);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mGirisBtn = findViewById(R.id.girisButonu);
        mGirisBtn.setOnClickListener( new View.OnClickListener() {


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

                //Kullanıcı kimliğini doğrulama

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Giris.this,"Giriş Başarılı!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Giris.this,"Yanlış email yada şifre girildi !" ,Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }

    public void girisSayfasinaGidisButonu() {
        mHesapOlusturmaBtn = findViewById(R.id.hesapOluşturma);

        mHesapOlusturmaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Kayit.class));
            }
        });


    }
}