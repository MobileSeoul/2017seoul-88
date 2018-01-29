package com.company.seoultest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    int total = 100; // 파싱 해 올 데이터 총 갯수.

    TextView title;
    ListView list;
    CustomList adapter;

    Document doc = null;
   // String _url;

    WebView webImg;
    WebSettings webSettings;

    String getGdata,getAdata,getDdata;
    String str_date;
    String str_date2;

    String[] titles = new String[total];
    String[] c = new String[total]; // 상세정보 담을 배열
    String[] u = new String[total]; //이미지 띄울 url 담을 배열
    public  String[] url = new String[total];

    boolean check ;
    //int toDayCompare1;
    //int toDayCompare2;

    public Date sDate=null;
    public Date eDate=null ;
    public Date checkToDate=null;
    public  Date toDay = null;

    long mNow;
    Date mDate;

    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat mFormat2 = new SimpleDateFormat("yyyy-MM-dd");



    public MainActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        checkDangerousPermissions();

//check =true;

        Intent intent = new Intent(this.getIntent());


        getGdata =intent.getStringExtra("sendGenre");
        getAdata = intent.getStringExtra("sendArea");
        getDdata = intent.getStringExtra("sendDate");

        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);

        str_date =  mFormat.format(mDate);
        str_date2 =  mFormat2.format(mDate);
        TextView todaytime = (TextView)findViewById(R.id.todaytime);
        todaytime.setText(str_date2);

       // makeText(getApplicationContext(),"GET : "+getGdata+"/"+getAdata+"/"+getDdata+"\n"+str_date,Toast.LENGTH_SHORT).show();


        GetXMLTask task = new GetXMLTask(this);
        //파싱해 올 url
        task.execute("http://openapi.seoul.go.kr:8088/7a746661526e616e3938586b5a466a/xml/SearchConcertDetailService/1/100/");




        adapter = new CustomList(MainActivity.this);

        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(MainActivity.this, DetailActivity.class);


                //아이템 position (그냥 순서라고 생각하면 됨 몇번째 아이템인지)
                for (int i=0;i<total;i++) {
                    // 터치한 리스트뷰 아이템이랑 파싱해 온 상세정보 담은 배열의 i번째가 같으면
                    if (position == i) {

                        //i번째 배열 데이터를 DetailActivity로 넘겨서 띄우는거거
                        intent.putExtra("infom", c[i]);
                        intent.putExtra("img", u[i]);
                        intent.putExtra("sendUrl", url[i]);
                      //  _url = url[position];

                        startActivity(intent);
                    }
                }



            }

        });

    }


    // private inner class extending AsyncTask
    @SuppressLint("NewApi")
    private class GetXMLTask extends AsyncTask<String, Void, Document>{
        private Activity context;

        public GetXMLTask(Activity context) {
            this.context = context;
        }


        @Override
        protected Document doInBackground(String... urls) {

            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db;

                db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                makeText(getBaseContext(), "Parsing Error",
                        Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {



            NodeList nodeList = doc.getElementsByTagName("row");


            for (int i = 0; i < nodeList.getLength(); i++) {


                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;



                NodeList sortList = fstElmnt.getElementsByTagName("CODENAME").item(0).getChildNodes();

                //나머지 아래 파싱은 다 똑같은 내용.
                if ((Node)sortList.item(0) == null){
                    titles[i] ="정보없음";
                }else {
                    titles[i] =((Node) sortList.item(0)).getNodeValue()  + " : ";

                }


                NodeList tList = fstElmnt.getElementsByTagName("TITLE");
                Element tElement = (Element) tList.item(0);
                tList = tElement.getChildNodes();

                titles[i]+= ((Node) tList.item(0)).getNodeValue();


                NodeList cnList = fstElmnt.getElementsByTagName("CODENAME").item(0).getChildNodes();
                if ((Node)cnList.item(0) == null){
                    c[i] ="";
                }else {

                    c[i] ="공연 제목 : "+((Node) cnList.item(0)).getNodeValue()  + " , ";
                }


                NodeList ttList = fstElmnt.getElementsByTagName("TITLE").item(0).getChildNodes();
                c[i]+= ((Node) ttList.item(0)).getNodeValue()+"\n";



                NodeList sdList = fstElmnt.getElementsByTagName("STRTDATE").item(0).getChildNodes();
                NodeList edList = fstElmnt.getElementsByTagName("END_DATE").item(0).getChildNodes();

// <---------------if문 조건 수정 ---------------->
                if (getDdata != null&&!getDdata.equals("")){
                    try{
                        sDate = mFormat.parse(((Node) sdList.item(0)).getNodeValue());
                        eDate= mFormat.parse(((Node) edList.item(0)).getNodeValue());
                        checkToDate = mFormat.parse(getDdata);
                        //   toDay = mFormat.parse(str_date); <-이거 지워도 됨.
                    }
                    catch (ParseException e) {
                        e.printStackTrace();}

                    int compare1 = sDate.compareTo(checkToDate);
                    int compare2 = eDate.compareTo(checkToDate);



                    if ((Node)sdList.item(0) == null){
                        c[i] ="";
                    }else {
                        if (compare1<=0&&compare2>=0){
                            c[i] +="기간 : "+((Node) sdList.item(0)).getNodeValue() +"~";

                        } else {
                            titles[i]="";
                            c[i]="";
                            continue;
                        }

                    }



                    if ((Node)edList.item(0) == null){
                        c[i] =""+"\n";
                    }else{
                        if (compare1<=0&&compare2>=0){
                            c[i] += ((Node) edList.item(0)).getNodeValue() + "" + "\n";
                        }else {
                            titles[i]="";
                            c[i]="";
                            continue;
                        }



                    }


                }
                else if (str_date != null){ // <---------------------------if문 조건 수정

                    //오늘 날짜값이 들어오면 오늘의 문화생활 띄우기.

                    try{
                        sDate = mFormat.parse(((Node) sdList.item(0)).getNodeValue());
                        eDate= mFormat.parse(((Node) edList.item(0)).getNodeValue());
                        toDay = mFormat.parse(str_date);
                    }
                    catch (ParseException e) {
                        e.printStackTrace();}


                    int  toDayCompare1 = sDate.compareTo(toDay);
                    int toDayCompare2 = eDate.compareTo(toDay);


                    if ((Node)sdList.item(0) == null){
                        c[i] ="";
                    }else if(toDayCompare1<=0&&toDayCompare2>=0) {
                        c[i] += "기간 : " + ((Node) sdList.item(0)).getNodeValue() + "~";
                    }else {
                        titles[i]="";
                        c[i] ="";
                        continue;
                    }


                    if ((Node)sdList.item(0) == null){
                        c[i] ="";
                    }else if(toDayCompare1<=0&&toDayCompare2>=0) {
                        c[i] += ((Node) edList.item(0)).getNodeValue() + "" + "\n";
                    }else {
                        titles[i]="";
                        c[i] ="";
                        continue;
                    }

                }


                NodeList rtList = fstElmnt.getElementsByTagName("USE_TRGT").item(0).getChildNodes();

                if ((Node)rtList.item(0) == null){
                    c[i] ="\n";
                }else{
                    c[i] += "연령 : "+((Node) rtList.item(0)).getNodeValue() + "" + "\n";

                }



                NodeList tpList = fstElmnt.getElementsByTagName("USE_FEE").item(0).getChildNodes();

                if ((Node)tpList.item(0) == null){
                    c[i] ="\n";
                }else{
                    c[i]+="요금 : "+ ((Node) tpList.item(0)).getNodeValue()+"\n";

                }



                NodeList gcList = fstElmnt.getElementsByTagName("GCODE").item(0).getChildNodes();

                if ((Node)gcList.item(0) == null){
                    c[i] ="\n";
                }else{
                    c[i]+="자치구 : "+ ((Node) gcList.item(0)).getNodeValue()+"\n";

                }


                NodeList olList = fstElmnt.getElementsByTagName("ORG_LINK").item(0).getChildNodes();

                if ((Node)olList.item(0) == null){
                    c[i] ="\n";
                }else{
                    c[i]+="원문 링크 : "+ ((Node) olList.item(0)).getNodeValue()+"\n";
                    url[i] = ((Node) olList.item(0)).getNodeValue();
                }

/*
<---------------------------------------------여기 다 지우고 ---------------------------------------------->
                if (getGdata==null||getAdata==null){
                  //  makeText(getApplicationContext(),"GET : "+i+"번째"+getGdata,Toast.LENGTH_SHORT).show();

                }else
                    if (!c[i].contains(getGdata)||!c[i].contains(getAdata)){

                    titles[i]="";
                    c[i]="";

                    continue;
                }

*/

                // 여기 아래 있는걸로 수정.

                if (getAdata!=null&&!c[i].contains(getAdata)||getGdata!=null&&!c[i].contains(getGdata)){

                    titles[i]="";
                    c[i]="";

                    continue;
                }




                NodeList imgList = fstElmnt.getElementsByTagName("MAIN_IMG");
                Element imgElement = (Element) imgList.item(0);
                imgList = imgElement.getChildNodes();


                u[i] = ((Node) imgList.item(0)).getNodeValue() ;



            }

        }


    }





    public class CustomList extends ArrayAdapter<String>{
        private final Activity context;
        public CustomList(Activity context){
            super(context,R.layout.listitem,titles);

            this.context = context;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem,null,true);

            title =(TextView)rowView.findViewById(R.id.title);

            webImg = (WebView) rowView.findViewById(R.id.web);

            webSettings = webImg.getSettings();
            webSettings.setJavaScriptEnabled(true);


            webImg.setFocusable(false);
            webImg.setClickable(false);

            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);


            title.setText(titles[position]);
            webImg.loadUrl(u[position]);



            return rowView;

        }
    }

    public void onClickSuitSearch(View v){


        Intent intent = new Intent(MainActivity.this, SuitActivity.class);
        startActivity(intent);
    }

    public void diary(View target) {
        Intent intent = new Intent(getApplicationContext(), DiaryActivity.class);
        startActivity(intent);
    }
/*
    public String RetunURL(){
        return _url;
    }
*/
private void checkDangerousPermissions() {
    String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    int permissionCheck = PackageManager.PERMISSION_GRANTED;
    for (int i = 0; i < permissions.length; i++) {
        permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            break;
        }
    }

    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
    } else {
        Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
            Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }
}

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
            }
        }
    }
}
