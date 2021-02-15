package com.example.step08customadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/*
    ListView 에 연결할 아답타 클래스 정의하기

    -BaseAdapter 추상 클래스를 상속 받아서 만든다.
*/
public class CountryAdapter extends BaseAdapter {
    //필드
    Context context;
    int layoutRes;
    List<CountryDto> list;

    //생성자
    public CountryAdapter(Context context, int layoutRes,List<CountryDto> list){
        //생성자의 인자로 전달된 값을 필드에 저장한다.
        this.context=context;
        this.layoutRes=layoutRes;
        this.list=list;
    }

    //모델의 아이템 갯수를 리턴한다.
    @Override
    public int getCount() {
        return list.size();
    }

    //인자로 전달된 position 에 인덱스에 해당하는 아이템 리턴
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //인자로 전달된 position 인덱스에 해당하는 아이템의 아이디(PK) 가
    //있으면 리턴, 없으면 그냥 position 을 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    //인자로 전달된 position 에 해당하는 cell view 를 만들어서 리턴한거나
    //이미 만들어진 cell view 의 내용만 만들어서 리턴해 준다.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
