package com.example.bitirmeprojesi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ItemsPreviewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    ItemsModel itemsModel;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    TextView yorumSayfasinaGecis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_preview);


        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        yorumSayfasinaGecis = findViewById(R.id.yorumSayfasinaGecis);





        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            itemsModel = (ItemsModel) intent.getSerializableExtra("items");

            textView.setText(itemsModel.getName());

            imageView2.setImageResource(itemsModel.getImages2());
            textView2.setText(itemsModel.getName1());

            imageView3.setImageResource(itemsModel.getImages3());
            textView3.setText(itemsModel.getName2());

            imageView4.setImageResource(itemsModel.getImages4());
            textView4.setText(itemsModel.getName3());
        }


        yorumSayfasinaGecis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), YorumActivity.class));
            }
        });
    }
}