package android.lab.nakhonpathom.adepter;

import android.content.Context;
import android.lab.nakhonpathom.R;
import android.lab.nakhonpathom.model.Place;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlaceListAdepter extends ArrayAdapter<Place> {  //Adapter นี้ทำงานกลับ Array ชนิด Place

    private Context mContext;
    private int mResource;
    private List<Place> mPlaceList;

    public PlaceListAdepter(@NonNull Context context, int resource, @NonNull List<Place> placeList) {
        super(context, resource, placeList);
        this.mContext=context;
        this.mResource=resource;
        this.mPlaceList=placeList;
    }

    //ctrl+o

    @NonNull
    @Override
    //ปรับเปลี่ยนการทำงานให้เปนแบบที่เราต้องการ
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       //ทำการ Inflate layout
        LayoutInflater inflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //ทำเพื่อแปลงเลเอ้าไฟลื เปน obj
        //View v= inflater.inflate(mResource,parent,false);
        View v= convertView; //ทำให้ประสิทธิภาพดีขึ้น
        if(v == null){
            v= inflater.inflate(mResource,parent,false);
        }

        //เข้าถึงobj place ตามposition ที่listView ขอมา
        Place place = mPlaceList.get(position);

        //กำหนดชื่อสถานที่ลงใน TextView (id =place_name_text_view)
        TextView placeNameTextView =v.findViewById(R.id.place_name_text_view);  //ก้อนชื่อสถานที่ มาเกบไว้ในตัวแปล v
        placeNameTextView.setText(place.name);

        //กำหนดอำเภอลงใน TextView (id =district_text_view)
        TextView districtTextView =v.findViewById(R.id.district_text_view);  //ก้อนอำเภอ มาเกบไว้ในตัวแปล v
        //districtTextView.setText("อำเภอ"+place.district);
        districtTextView.setText("อำเภอ".concat(place.district));

        ImageView logoImageView = v.findViewById(R.id.logo_image_view);
        logoImageView.setImageResource(place.imageRes);

        return v;
        // return super.getView(position, convertView, parent);


    }
}
