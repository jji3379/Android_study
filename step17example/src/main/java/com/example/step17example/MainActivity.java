package com.example.step17example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Util.RequestListener, AdapterView.OnItemClickListener {

    List<String> list;
    ArrayAdapter<String> adapter;
    JSONArray arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //아답타에 연결할 모델
        list=new ArrayList<>();
        list.add("1 김구라 노량진");
        list.add("2 해골 행신동");
        //ListView 에 연결할 아답타
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        //ListView 의 참조값 얻어와서 아답타 연결하기
        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);
        //ListView 에 아이템 클릭 리스너 등록
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //ListView 에 출력할 데이터를 서버로 부터 요청을 한다.
        String url="http://172.30.1.46:8888/spring04/api/member/list.do";
        Util.sendGetRequest(1,url,null,this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //서버가 응답한 JSON 문자열
        String data=(String)result.get("data");

        switch (requestId){
            case 1:
                //data 는 [ {},{},{},... ] 형식의 JSON 문자열이다.
                try {
                    //List 에 있는 내용을 일단 모두 삭제하고
                    list.clear();
                    //새로 넣어준다.
                    arr=new JSONArray(data);
                    for(int i=0; i<arr.length(); i++){
                        JSONObject obj=arr.getJSONObject(i);
                        int num=obj.getInt("num");
                        String name=obj.getString("name");
                        String addr=obj.getString("addr");
                        list.add(num+" | "+name+" | "+addr);
                    }
                    //모델이 업데이트 되었다고 아답타에 알린다.
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
                case 2:
                    //data는 {} 형식의 문자열이다.
                    try {
                        JSONObject obj=new JSONObject(data);
                        int num=obj.getInt("num");
                        String name=obj.getString("name");
                        String addr=obj.getString("addr");
                        //회원의 번호, 이름, 주소를 MemberDto 객체에 담고
                        MemberDto dto=new MemberDto(num, name, addr);
                        //자세히 보기 Activity 로 이동할 Intent 객체에 담고
                        Intent intent=new Intent(this,DetailActivity.class);
                        intent.putExtra("dto",dto);
                        //자세히 보기 Activity 로 이동한다.
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //클릭한 셀의 인덱스값을 이용해서 JSONObject 객체의 참조값 얻어오기
        try {
            JSONObject obj=arr.getJSONObject(position);
            //클릭한 셀의 회원번호
            int num=obj.getInt("num");
            //번호를 이용해서 회원 한명의 정보를 다시 요청한다.
            String url="http://172.30.1.46:8888/spring04/api/member/detail.do";
            Map<String, String> params=new HashMap<>();
            params.put("num",Integer.toString(num));
            //Util 을 이용해서 요청하기
            Util.sendGetRequest(2,url,params,this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}