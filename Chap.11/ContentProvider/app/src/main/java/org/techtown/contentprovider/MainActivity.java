package org.techtown.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView);

        Button button1=findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertStudent();
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryStudent();
            }
        });

        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudent();
            }
        });

        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStudent();
            }
        });
    }

    public void insertStudent(){
        try{
        println("insert 연산 호출");
        String uriString ="content://org.techtown.contentprovider/student";
        Uri uri=new Uri.Builder().build().parse(uriString);

        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
        String[] columns=cursor.getColumnNames();
        println("columns 개수: "+columns.length);

        for(int i=0;i<columns.length;i++){
            println("#"+i+" : "+columns[i]);
        }

        ContentValues values=new ContentValues();
        values.put("name","john");
        values.put("age",20);
        values.put("mobile","010-1234-5678");
        uri=getContentResolver().insert(uri,values);
        println("insert 결과값: "+uri.toString());}
        catch (Exception e){println("오류발생 : "+e.getMessage());}
    }

    public void queryStudent(){
        try {
            String uriString = "content://org.techtown.contentprovider/student";
            Uri uri = new Uri.Builder().build().parse(uriString);
            String[] columns = new String[] {"name", "age", "mobile"};
            Cursor cursor = getContentResolver().query(uri, columns, null, null, "name ASC");
            println("query 결과: " + cursor.getCount());

            int index = 0;
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(columns[0]));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
                @SuppressLint("Range") String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));
                println("#" + index + " : " + name + " , " + age + " , " + mobile);
                index += 1;
            }
        }catch (Exception e){ println("오류발생 : "+e.getMessage());}
    }

    public void updateStudent(){
        String uriString = "content://org.techtown.contentprovider/student";
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection="mobile = ?";
        String[] selectionArgs=new String[]{"010-1234-5678"};
        ContentValues updateValue=new ContentValues();
        updateValue.put("mobile","010-2000-2000");
        int count=getContentResolver().update(uri,updateValue,selection,selectionArgs);
        println("update 결과: "+count);
    }

    public void deleteStudent(){
        String uriString = "content://org.techtown.contentprovider/student";
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection="name = ?";
        String[] selectionArgs=new String[]{"john"};

        int count=getContentResolver().delete(uri,selection,selectionArgs);
        println("delete결과 : "+count);
    }
    public void println(String str){
        tv.append(str+"\n");
    }


}