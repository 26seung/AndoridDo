package com.example.adapterviewexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 데이터 준비
//        ArrayList<String> data = new ArrayList<>();
//        for(int i =0; i<30; i++){
//            data.add("data" + i);
//        }
        
        ArrayList<Weather> data = new ArrayList<>();
        data.add(new Weather("수원", "25도", "맑음"));
        data.add(new Weather("서울", "30도", "흐림"));
        data.add(new Weather("경기", "32도", "비"));
        data.add(new Weather("송파", "35도", "눈"));
        data.add(new Weather("하남", "20도", "번개"));
        

        // 어탭터
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        
        MyFirstAdapter adapter = new MyFirstAdapter(data);

        // 뷰
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        // 클릭
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,position+"번째",Toast.LENGTH_SHORT).show();
            }
        });

    }
}