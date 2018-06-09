package com.example.stud.musicapp.topsongs;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.stud.musicapp.R;
import com.example.stud.musicapp.api.ApiService;
import com.example.stud.musicapp.api.TrendingList;
import com.example.stud.musicapp.api.TrendingSingle;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.support.v7.widget.RecyclerView;

public class TopSongsActivity extends AppCompatActivity {

    List<TrendingSingle> singles = new ArrayList<>(0);

    RecyclerView rvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_songs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvList = findViewById(R.id.rvList);
        
        Call<TrendingList> trendingListCall = ApiService. getService ().getTrendingList( "us" ,
                "itunes" , "singles" );
        trendingListCall.enqueue( new Callback<TrendingList>() {
            @Override
            public void onResponse(@NonNull Call<TrendingList> call, @NonNull
                    Response<TrendingList> response) {
                    TrendingList trendingList = response.body();

                    if(trendingList != null && trendingList.trending != null){
                        singles = trendingList.trending;

                    }
                    setList();
                //Log. d ( "TAG" , new Gson().toJson(trendingList));
            }
            @Override
            public void onFailure( @NonNull Call<TrendingList> call, Throwable t) {
                Toast. makeText (TopSongsActivity. this , "Blad pobierania danych: " +
                        t.getLocalizedMessage(), Toast. LENGTH_SHORT ).show();
            }
        });
    }


    private void setList(){
        TopSongsAdapter topSongsAdapter = new TopSongsAdapter(singles);
        rvList.setAdapter(topSongsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        rvList.setLayoutManager(linearLayoutManager);

        rvList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}
