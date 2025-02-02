package org.techtown.album;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=findViewById(R.id.imageView);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    public void openGallery(){
        Intent intent=new Intent();
        intent.setType("image/*"); // 이미지데이터를 가져오라
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101){
            if(resultCode==RESULT_OK){
                Uri fileUri=data.getData();
                ContentResolver resolver=getContentResolver();

                try{
                    InputStream inputStream=resolver.openInputStream(fileUri);
                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                    iv.setImageBitmap(bitmap);
                    inputStream.close();
                }catch(Exception e){e.printStackTrace();}
            }
        }
    }
}