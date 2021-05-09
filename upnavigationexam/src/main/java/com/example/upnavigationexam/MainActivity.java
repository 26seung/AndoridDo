package com.example.upnavigationexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveParentActivity(View view) {
        startActivity(new Intent(this,ParentActivity.class));
    }

//    옵션 메뉴를 생성 하는 메서드   ... boolean 을 통해 리턴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


//    옵션 메뉴 처리 메서드 ..  일반적으로  if 문이나 switch 문을 사용 .. MenuItem item은 클릭된 아이탬의 객체가 넘어옴
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_menu1:
                Toast.makeText(this,"첫 번째 메뉴",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_menu2:
                Toast.makeText(this,"두 번째   메뉴",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}