package com.example.intent_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU=101;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_MENU){
            Toast.makeText(getApplicationContext(),"onActivityResult 메서드 호출됨, 요청코드: "
            +requestCode+", 결과코드: "+resultCode,Toast.LENGTH_LONG).show();
        }
        if(resultCode==RESULT_OK){
            String name=data.getStringExtra("name");
            Toast.makeText(getApplicationContext(),"응답으로 전달된 name : "+name,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivityForResult(intent,REQUEST_CODE_MENU);
            }
        });
    }
}
