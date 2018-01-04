package io.github.hyuwah.restphoto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import io.github.hyuwah.restphoto.R;

public class MainActivity extends AppCompatActivity {

    Button btnGetSingle;
    Button btnGetMultiple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetSingle = (Button) findViewById(R.id.button_get_single);
        btnGetMultiple = (Button) findViewById(R.id.button_get_multiple);

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
