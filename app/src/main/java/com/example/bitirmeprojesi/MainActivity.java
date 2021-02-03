package com.example.bitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    String names[] = {"ankara","antalya","hatay","istanbul","izmir"};
    String emails[] = {"Başkent","Turizm'in Kalbi","Medeniyetler Şehri","Dünyanın En Güzel Şehri","Gönlünce Hareket Eder"};
    int images[] = {R.drawable.ankara,R.drawable.antalya,R.drawable.hatay,R.drawable.istanbul,R.drawable.izmir};
    int images2[] = {R.drawable.anitkabir,R.drawable.dudenselalesi,R.drawable.arkeolojimuzesi,R.drawable.ayasofya,R.drawable.karagol};
    int images3[] = {R.drawable.kizilay,R.drawable.konyaaltisahili,R.drawable.stpierre,R.drawable.dolmabahce,R.drawable.saatkulesi};
    int images4[] = {R.drawable.kocatepecami,R.drawable.hadrian,R.drawable.tinustuneli,R.drawable.galata,R.drawable.cumhuriyet};
    String names1[] = {"1-)Anıtkabir","1-)Duden Şelalesi","1-)Arkeoloji Müzesi","1-)Ayasofya","1-)Karagöl"};
    String names2[] = {"2-)Kızılay Meydanı","2-)Konyaaltı Sahili","2-)Saint Pierre Kilisesi","2-)Dolmabahçe Sarayi","2-)Saat Kulesi"};
    String names3[] = {"3-)Kocatepe Cami","3-)Hadrian Kale Kapısı","3-)Tinus Tuneli","3-)Galata Kulesi","3-)Cumhuriyet Meydanı"};


    List<ItemsModel> itemsModelList = new ArrayList<>();

    ListView listView;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);

     /*  FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("uyeler").child("userid").setValue(1);
        myRef.child("uyeler").child("username").setValue("emrah");*/



        for(int i = 0;i < names.length;i++){

            ItemsModel itemsModel = new ItemsModel(names[i],emails[i],images[i],images2[i],images3[i],images4[i],names1[i],names2[i],names3[i]);

            itemsModelList.add(itemsModel);

        }

        customAdapter = new CustomAdapter(itemsModelList,this);

        listView.setAdapter(customAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.arama_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("Main"," data search"+newText);

                customAdapter.getFilter().filter(newText);

                return true;
            }
        });


        return true;
    }

//toolbar tıklama olayları
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.searchView){
            return true;
        }else if(item.getItemId()==R.id.maps){
            startActivity(new Intent(getApplicationContext(),MapsActivity.class));
        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;

    }

//filtreleme
    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<ItemsModel> itemsModelsl;
        private List<ItemsModel> itemsModelListFiltered;
        private Context context;

        public CustomAdapter(List<ItemsModel> itemsModelsl, Context context) {
            this.itemsModelsl = itemsModelsl;
            this.itemsModelListFiltered = itemsModelsl;
            this.context = context;
        }


        @Override
        public int getCount() {
            return itemsModelListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return itemsModelListFiltered.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.satir_items,null);


            TextView names = view.findViewById(R.id.name);
            ImageView imageView = view.findViewById(R.id.images);

            names.setText(itemsModelListFiltered.get(position).getName());
            imageView.setImageResource(itemsModelListFiltered.get(position).getImages());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("main activity","item clicked");
                    startActivity(new Intent(MainActivity.this,ItemsPreviewActivity.class).putExtra("items",itemsModelListFiltered.get(position)));

                }
            });

            return view;
        }



        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();
                    if(constraint == null || constraint.length() == 0){
                        filterResults.count = itemsModelsl.size();
                        filterResults.values = itemsModelsl;

                    }else{
                        List<ItemsModel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for(ItemsModel itemsModel:itemsModelsl){
                            if(itemsModel.getName().contains(searchStr) || itemsModel.getEmail().contains(searchStr)){
                                resultsModel.add(itemsModel);

                            }
                            filterResults.count = resultsModel.size();
                            filterResults.values = resultsModel;
                        }


                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    itemsModelListFiltered = (List<ItemsModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }

}