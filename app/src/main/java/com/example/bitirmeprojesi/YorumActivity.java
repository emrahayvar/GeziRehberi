package com.example.bitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class YorumActivity extends AppCompatActivity {

    TextView textGonder;
    EditText editYorum;
    RatingBar ratingBar;
    ArrayList<User> users;
    ListAdapter listAdapter2;
    ListView listYorum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yorum);
        textGonder = findViewById(R.id.textGonder);
        editYorum = findViewById(R.id.editYorum);
        ratingBar = findViewById(R.id.ratingBar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        users=new ArrayList<>();
        listAdapter2 = new ListAdapter(this,users);
        listYorum = findViewById(R.id.listYorum);



        textGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textGonder.setVisibility(View.INVISIBLE);
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("users");
                dbRef.push().setValue(
                        new User(
                                editYorum.getText().toString()
                        )
                );
                User user = new User();
                dbRef.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("Yorum Eklendi");
                        editYorum.setText("");
                        textGonder.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("Yorum eklenemedi: " +e.getMessage());
                    }
                });
                listYorum.setAdapter(listAdapter2);

            }
        });

        myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    User user = postSnapshot.getValue(User.class);
                    //String yorum
                    users.add(
                            new User(
                                    user.getYorum()
                            )
                    );

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG);
    }


    public class ListAdapter extends BaseAdapter{
        ArrayList<User> users;
        LayoutInflater layoutInflater;
        Context context;

        public ListAdapter(Activity activity, ArrayList<User> users){
            this.users = users;
            this.context=activity;
            this.layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = layoutInflater.inflate(R.layout.yorum_items,null);
            TextView kullaniciYorum = (TextView)view.findViewById(R.id.kullaniciYorum);

            kullaniciYorum.setText(users.get(position).getYorum());

            return view;
        }
    }



}