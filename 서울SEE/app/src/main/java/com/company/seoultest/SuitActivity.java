package com.company.seoultest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 임동주 on 2017-10-11.
 */

public class SuitActivity extends Activity {

    Button gukak,solo,dance,musical,culture,theater,movie,art,festval,classic,consert,etc;
    Button gangseo, gangdong, gangbuk, gangnam, guro, geumchon, gwangjin, gwanak,
            nowon, dongjak, dongdeamun, dobong, mapo, seodeamun,sungdong,sungbuk,seocho,songpa,
            yangcheon,yungdongpo,eunpyeong,yongsan,jongno,jung,jungrang;
    EditText inputDete;

    String getGenre,getArea,getDate;
    int ocw,mw,mocw,tmw,tmocw,thmw,max;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suit);

        inputDete = (EditText)findViewById(R.id.date);
    }
    public void onClickGenre(View v){

        switch (v.getId()){
            case R.id.gukak:
                gukak = (Button)findViewById(R.id.gukak);
                getGenre = gukak.getText().toString();
                break;
            case R.id.solo:

                solo = (Button)findViewById(R.id.solo);
                getGenre = solo.getText().toString();
                break;
            case R.id.dance:

                dance = (Button)findViewById(R.id.dance);
                getGenre = dance.getText().toString();
                break;
            case R.id.musical:

                musical = (Button)findViewById(R.id.musical);
                getGenre = musical.getText().toString();
                break;

            case R.id.movie:

                movie = (Button)findViewById(R.id.movie);
                getGenre = movie.getText().toString();
                break;

            case R.id.culture:

                culture = (Button)findViewById(R.id.culture);
                getGenre = culture.getText().toString();
                break;

            case R.id.theater:

                theater = (Button)findViewById(R.id.theater);
                getGenre = theater.getText().toString();

                break;

            case R.id.art:

                art = (Button)findViewById(R.id.art);
                getGenre = art.getText().toString();
                break;
            case R.id.festival:

                festval = (Button)findViewById(R.id.festival);
                getGenre = festval.getText().toString();

                break;
            case R.id.classic:

                classic = (Button)findViewById(R.id.classic);
                getGenre = classic.getText().toString();
                break;
            case R.id.consert:

                consert = (Button)findViewById(R.id.consert);
                getGenre = consert.getText().toString();
                break;
            case R.id.etc:

                etc = (Button)findViewById(R.id.etc);
                getGenre = etc.getText().toString();
                break;
        }
        getGenre = getGenre.substring(1);
        Toast.makeText(getApplicationContext(), getGenre + "가 선택 되었습니다.",Toast.LENGTH_SHORT).show();
    }
    public void onClickArea(View v){
        switch (v.getId()) {
            case R.id.gangseo:
                gangseo = (Button) findViewById(R.id.gangseo);
                getArea = gangseo.getText().toString();
                break;

            case R.id.gangdong:
                gangdong = (Button) findViewById(R.id.gangdong);
                getArea = gangdong.getText().toString();
                break;

            case R.id.gangbuk:
                gangbuk = (Button) findViewById(R.id.gangbuk);
                getArea = gangbuk.getText().toString();
                break;

            case R.id.gangnam:
                gangnam = (Button) findViewById(R.id.gangnam);
                getArea = gangnam.getText().toString();
                break;

            case R.id.guro:
                guro = (Button) findViewById(R.id.guro);
                getArea = guro.getText().toString();
                break;

            case R.id.geumcheon:
                geumchon = (Button) findViewById(R.id.geumcheon);
                getArea = geumchon.getText().toString();
                break;

            case R.id.gwangjin:
                gwangjin = (Button) findViewById(R.id.gwangjin);
                getArea = gwangjin.getText().toString();
                break;

            case R.id.gwanak:
                gwanak = (Button) findViewById(R.id.gwanak);
                getArea = gwanak.getText().toString();
                break;

            case R.id.nowon:
                nowon = (Button) findViewById(R.id.nowon);
                getArea = nowon.getText().toString();
                break;

            case R.id.dongjak:
                dongjak = (Button) findViewById(R.id.dongjak);
                getArea = dongjak.getText().toString();
                break;

            case R.id.dongdeamun:
                dongdeamun = (Button) findViewById(R.id.dongdeamun);
                getArea = dongdeamun.getText().toString();
                break;

            case R.id.dobong:
                dobong = (Button) findViewById(R.id.dobong);
                getArea = dobong.getText().toString();
                break;
            case R.id.mapo:
                mapo = (Button) findViewById(R.id.mapo);
                getArea = mapo.getText().toString();
                break;
            case R.id.seodeamun:
                seodeamun = (Button) findViewById(R.id.seodeamun);
                getArea = seodeamun.getText().toString();
                break;
            case R.id.sungdong:
                sungdong = (Button) findViewById(R.id.sungdong);
                getArea = sungdong.getText().toString();
                break;
            case R.id.sungbuk:
                sungbuk = (Button) findViewById(R.id.sungbuk);
                getArea = sungbuk.getText().toString();
                break;
            case R.id.seocho:
                seocho = (Button) findViewById(R.id.seocho);
                getArea = seocho.getText().toString();
                break;
            case R.id.songpa:
                songpa = (Button) findViewById(R.id.songpa);
                getArea = songpa.getText().toString();
                break;
            case R.id.yangcheon:
                yangcheon = (Button) findViewById(R.id.yangcheon);
                getArea = yangcheon.getText().toString();
                break;
            case R.id.youngdeungpo:
                yungdongpo= (Button) findViewById(R.id.youngdeungpo);
                getArea = yungdongpo.getText().toString();
                break;
            case R.id.eunpyeong:
                eunpyeong = (Button) findViewById(R.id.eunpyeong);
                getArea = eunpyeong.getText().toString();
                break;
            case R.id.yongsan:
                yongsan = (Button) findViewById(R.id.yongsan);
                getArea = yongsan.getText().toString();
                break;

            case R.id.jongno:
                jongno = (Button) findViewById(R.id.jongno);
                getArea = jongno.getText().toString();
                break;
            case R.id.jung:
                jung = (Button) findViewById(R.id.jung);
                getArea = jung.getText().toString();
                break;
            case R.id.jungrang:
                jungrang = (Button) findViewById(R.id.jungrang);
                getArea = jungrang.getText().toString();
                break;
        }
        getArea = getArea.substring(1);
        Toast.makeText(getApplicationContext(), getArea + "가 선택 되었습니다.",Toast.LENGTH_SHORT).show();
    }

    public void onClickSelect(View v){
        Intent intent = new Intent(SuitActivity.this,MainActivity.class);


        getDate = inputDete.getText().toString();

        intent.putExtra("sendGenre",getGenre);
        intent.putExtra("sendArea",getArea);
        intent.putExtra("sendDate",getDate);

        Toast.makeText(getApplicationContext(),getGenre+"/"+getArea+"/"+getDate,Toast.LENGTH_SHORT).show();

        startActivity(intent);

    }
    public void home(View target) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void back(View target) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
