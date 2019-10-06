package android.lab.nakhonpathom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        Intent intent =getIntent();
        //key ที่รับมาต้องชื่อตรงกันด้วยนะ
        String placeName =intent.getStringExtra("name");        //ไม่เจอจะรีเทรน null ออกมา   เปนobj type
        int placeImageRes =intent.getIntExtra("image",0);  //int ต้องระบุค่าดีฟลอด้วย เมื่อไม่เจอค่าจะรีเทรนค่า0 ออกมา

        ImageView coverImageView =findViewById(R.id.cover_image_view);
        coverImageView.setImageResource(placeImageRes);

        TextView placeNameTextView = findViewById(R.id.place_name_text_view);
        placeNameTextView.setText(placeName);
    }
}
