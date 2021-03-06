package com.example.webviewexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mAddressEdit;
    private WebView mWebView;
    private Button mMoveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddressEdit = findViewById(R.id.address_edit);
        mWebView = findViewById(R.id.web_view);
        mMoveButton = findViewById(R.id.move_button);

        // 웹뷰 설정
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                Toast.makeText(MainActivity.this,"로딩 끝",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }
        });

        // 돋보기 클릭시 검색 기능
        mAddressEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    mMoveButton.callOnClick();

                    // 키보드 숨기기
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                    return true;
                }
                return false;
            }
        });
    }

    public void onClick(View view) {

        String address= mAddressEdit.getText().toString();

        if(address.startsWith("http://") == false){
            address = "http://" + address;
        }
        mWebView.loadUrl(address);

    }

    // 뒤로 가기 재정의 ..  히스토리 참조하여 뒤로가기
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }
    // 메뉴 옵션 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    // 메뉴 옵션.. 뒤로가기 앞으로 가기 새로고침 메서드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_back:
                    mWebView.goBack();
                return true;
            case R.id.action_foward:
                    mWebView.goForward();
                return true;
            case R.id.action_refresh:
                mWebView.reload();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}