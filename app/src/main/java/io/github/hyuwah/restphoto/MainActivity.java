package io.github.hyuwah.restphoto;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    Button btnGetSingle;
    Button btnGetMultiple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetSingle = (Button) findViewById(R.id.btnSingle);
        btnGetMultiple = (Button) findViewById(R.id.btnMany);

        btnGetSingle.setOnClickListener(view->{
            Intent intent = new Intent(this, GetSingleActivity.class);
            startActivity(intent);
        });

        btnGetMultiple.setOnClickListener(view->{
            Intent intent = new Intent(this, GetMultipleActivity.class);
            startActivity(intent);
        });

    }
}
