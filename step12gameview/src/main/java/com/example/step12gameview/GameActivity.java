package com.example.step12gameview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //GameView 객체를 생성해서
        GameView view=new GameView(this);
        //화면을 GameView 로 모두 채운다
        setContentView(view);
    }
}
