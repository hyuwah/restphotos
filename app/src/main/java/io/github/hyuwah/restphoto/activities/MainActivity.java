package io.github.hyuwah.restphoto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import io.github.hyuwah.restphoto.R;

public class MainActivity extends AppCompatActivity {

    ImageButton btnGetSingle;
    ImageButton btnGetMultiple;
    ImageButton btnGenProfileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetSingle = findViewById(R.id.activity_main_btn_get_single);
        btnGetMultiple = findViewById(R.id.activity_main_btn_get_multiple);
        btnGenProfileImg = findViewById(R.id.activity_main_btn_gen_profile_img);

        btnGetSingle.setOnClickListener(view->{
            Intent intent = new Intent(this, GetSingleActivity.class);
            startActivity(intent);
        });

        btnGetMultiple.setOnClickListener(view->{
            Intent intent = new Intent(this, GetMultipleActivity.class);
            startActivity(intent);
        });

        btnGenProfileImg.setOnClickListener(view -> {
            Intent intent = new Intent(this, RobohashActivity.class);
            startActivity(intent);
        });

    }
}
