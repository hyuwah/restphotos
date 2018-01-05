package io.github.hyuwah.restphoto.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import io.github.hyuwah.restphoto.R;

// TODO: on keyboard enter, click generate button
// TODO: on click generate, hide keyboard if visible
// TODO: add option to choose 4 style set

public class RobohashActivity extends AppCompatActivity {

    Button btnGenerate;
    EditText etName;
    ImageView ivProfile;
    TextView tvName;
    Spinner spnSpinner;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robohash);

        btnGenerate = findViewById(R.id.robohash_activity_btn_generate);
        etName = findViewById(R.id.robohash_activity_et_name);
        ivProfile = findViewById(R.id.robohash_activity_iv_profile);
        tvName = findViewById(R.id.robohash_activity_tv_name);
        spnSpinner = findViewById(R.id.robohash_activity_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ROBOHASH_STYLE_SET, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spnSpinner.setAdapter(adapter);

        btnGenerate.setOnClickListener(view -> {

            name = etName.getText().toString();
            String robothashUrl = "https://robohash.org/";
            String robothashStyle = spnSpinner.getSelectedItem().toString();
            Log.i("debug", "onCreate: "+robothashStyle);
            String robothashStyleUrl = "";

            switch(robothashStyle){
                case "Robot":
                    robothashStyleUrl = "";
                    break;
                case "Monster":
                    robothashStyleUrl ="?set=set2";
                    break;
                case "Robot Head":
                    robothashStyleUrl ="?set=set3";
                    break;
                case "Cat":
                    robothashStyleUrl ="?set=set4";
                    break;
            }

            if (!name.isEmpty()) {
                // Rendering with Picasso

                Toast.makeText(this, "Fetching image...", Toast.LENGTH_SHORT).show();
                tvName.setText("Loading...");


                Picasso.with(this)
                        .load(robothashUrl + name + robothashStyleUrl)
                        .error(R.drawable.img_error)
                        .placeholder(R.drawable.img_placeholder)
                        .resize(360,360)
                        .transform(new PicassoCircleTransformation())
                        .into(ivProfile, new Callback() {
                            @Override
                            public void onSuccess() {
                                tvName.setText(name);
                            }

                            @Override
                            public void onError() {
                                Toast.makeText(getBaseContext(), "Failed to fetch image...\nCheck device connectivity / Illegal name", Toast.LENGTH_SHORT).show();
                                tvName.setText("Error");
                            }
                        });




            } else {
                Toast.makeText(this, "Type some name", Toast.LENGTH_SHORT).show();
            }

        });
    }


    public class PicassoCircleTransformation implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }


}
