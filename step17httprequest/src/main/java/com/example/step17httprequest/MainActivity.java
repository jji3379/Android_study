package com.example.step17httprequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements Util.RequestListener{
    EditText console;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        console=findViewById(R.id.console);
        Button requestBtn=findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String url="http://172.30.1.46:8888/AndroidServer/info.jsp";
                Util.sendGetRequest(1, url, null, MainActivity.this);
            }
        });
        //요청하기2
        Button requestBtn2=findViewById(R.id.requestBtn2);
        requestBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String url="http://172.30.1.46:8888/AndroidServer/info2.jsp";
                Util.sendGetRequest(2, url, null, MainActivity.this);
            }
        });
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //응답 코드값
        int code=(int)result.get("code");
        //응답된 문자열 JSON
        String data=(String)result.get("data");
        //요청의 아이디값을 이용해서 분기한다.
        switch (requestId){
            case 1:
                /*
                    JSONObject : { }
                    JSONArray : [ ]
                 */
                try {
                    // data 는 {} 형식의 문자열이기 때문에 JSONObject 객체생성하면서
                    // 생성자의 인자로 data 를 전달한다.
                    JSONObject obj=new JSONObject(data);
                    // "num" 이라는 키값으로 저장된 int 얻어내기
                    int num=obj.getInt("num");
                    // "name" 이라는 키값으로 저장된 String 얻어내기
                    String name=obj.getString("name");
                    // "isMan" 이라는 키값으로 저장된 boolean 얻어내기
                    boolean isMan=obj.getBoolean("isMan");
                    console.setText("번호:"+num+" 이름:"+name+" 남자여부:"+isMan);
                } catch (JSONException e) { // data 가 JSON 형식이 아니라면 예외가 발생한다.
                    e.printStackTrace();
                }
                break;
            case 2:
                try{
                    //data 는 [] 형식이므로 JSONArray 객체를 생성하면서
                    //생성자의 인자로 data 를 전달한다.
                    JSONArray arr=new JSONArray(data);
                    //일단 지우고
                    console.setText("");
                    //JSONArray 의 사이즈 만큼 반복문을 돌면서
                    for(int i=0; i<arr.length(); i++){
                        //문자열을 순서대로 참조한다.
                        String tmp=arr.getString(i);
                        //참조된 문자열을 출력하기
                        console.append(tmp+"\n");
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
                break;
        }

        /*
            JSONObject : { }
            JSONArray : [ ]
         */

        try {
            // data 는 {} 형식의 문자열이기 때문에 JSONObject 객체생성하면서
            // 생성자의 인자로 data 를 전달한다.
            JSONObject obj=new JSONObject(data);
            // "num" 이라는 키값으로 저장된 int 얻어내기
            int num=obj.getInt("num");
            // "name" 이라는 키값으로 저장된 String 얻어내기
            String name=obj.getString("name");
            // "isMan" 이라는 키값으로 저장된 boolean 얻어내기
            boolean isMan=obj.getBoolean("isMan");
            console.setText("번호:"+num+" 이름:"+name+" 남자여부:"+isMan);
        } catch (JSONException e) { // data 가 JSON 형식이 아니라면 예외가 발생한다.
            e.printStackTrace();
        }

    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        //예외 메세지 읽어오기
        String data=(String)result.get("data");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}