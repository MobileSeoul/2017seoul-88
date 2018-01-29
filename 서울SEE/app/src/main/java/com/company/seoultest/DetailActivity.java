package com.company.seoultest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by 임동주 on 2017-10-11.
 */

public class DetailActivity extends Activity {
    WebView webImg2;
    WebSettings webSettings2;
    TextView content;
    String infomation,imgUrl,getUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        content = (TextView)findViewById(R.id.content);
        webImg2  =(WebView)findViewById(R.id.web2);

        Intent Intent = new Intent(this.getIntent());

        infomation = Intent.getStringExtra("infom");
        imgUrl = Intent.getStringExtra("img");
        getUrl = Intent.getStringExtra("sendUrl");



        webSettings2 = webImg2.getSettings();
        webSettings2.setJavaScriptEnabled(true);

        webImg2.setFocusable(false);
        webImg2.setClickable(false);

        webSettings2.setLoadWithOverviewMode(true);
        webSettings2.setUseWideViewPort(true);

        webImg2.loadUrl(imgUrl);

        content.setText(infomation);

    }
    public void home(View target) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void back(View target) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void LinkButtonClick(View v){
        //MainActivity main = new MainActivity();
        //String url = main.RetunURL();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getUrl));
        Toast.makeText(getApplicationContext(), "원문링크로 이동하였습니다.", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }




}
