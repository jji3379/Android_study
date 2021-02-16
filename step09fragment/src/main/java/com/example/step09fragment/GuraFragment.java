package com.example.step09fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/*
    [ Fragment 만드는 방법]
    1. Fragment 클래스를 상속 받는다.
    2. 프래그먼트의 layout xml 문서를 만든다.
    3. onCreateView() 메소드를 오버라이딩해서 프래그
*/
public class GuraFragment extends Fragment implements View.OnClickListener {

    //필요한 필드 정의하기
    TextView textView;
    int count;
    //프래그먼트가 관리할 화면 View 를 만들어서 리턴해 줘야 한다.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //인자로 전달되는 레이아웃 전개자 객체를 이용해서 View 를 만들어서
        View view=inflater.inflate(R.layout.fragment_gura,container);
        //만든 View 에서 TextView 의 참조값을 얻어온다.
        textView=view.findViewById(R.id.textView);
        textView.setOnClickListener(this);
        //리턴해 준다.
        return view;
    }

    @Override
    public void onClick(View v) {
        count++;
        textView.setText(String.valueOf(count));
    }
}
