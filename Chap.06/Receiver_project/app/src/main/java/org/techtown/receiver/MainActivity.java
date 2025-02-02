package org.techtown.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }

    public void onDenied(int requestCode,String[] permissions){
        Toast.makeText(this,"permissions denied: "+permissions.length,Toast.LENGTH_LONG).show();

    }

    public void onGranted(int requestCode,String[] permissions){
        Toast.makeText(this,"permission granted: "+permissions.length,Toast.LENGTH_SHORT).show();
    }
}