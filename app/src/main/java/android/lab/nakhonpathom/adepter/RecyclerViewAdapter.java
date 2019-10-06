package android.lab.nakhonpathom.adepter;

import android.content.Context;
import android.content.Intent;
import android.lab.nakhonpathom.PlaceDetailsActivity;
import android.lab.nakhonpathom.R;
import android.lab.nakhonpathom.model.Place;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private int mResource;
    private List<Place> mPlaceList;

    public RecyclerViewAdapter(Context mContext, int mResource, List<Place> mPlaceList) { //ส่งเข้ามาแต่ละอัน
        this.mContext = mContext;
        this.mResource = mResource;
        this.mPlaceList = mPlaceList;
    }

    //มีเมธอดที่บังคับให้ โอเวอร์ลาย onCreateViewHolder,onBindViewHolder,getItemCount ทำเสมือน getViewของlist
    //กด ctrl + o
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mResource,parent,false); //ไม่ต้องคุมว่ารียูสหรือไม่ มันทำให้อัตโนมัติ
        return new MyViewHolder(view);//ไม่ได้รีเทรน view ตรงๆ
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Place place = mPlaceList.get(position);
        //set ข้อมูลยัดตรงนี้
        holder.nameTextView.setText(place.name);
        holder.districtTextView.setText(place.district);
        holder.placeImageView.setImageResource(place.imageRes);

        holder.place=place;
    }

    @Override
    public int getItemCount() {//ต้องระบุขนาดข้อมูลด้วย
        return mPlaceList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{  //inner class
        //ประกาศ ตรงนี้
        private ImageView placeImageView;
        private TextView  nameTextView;
        private TextView  districtTextView;


        private Place place;

        public MyViewHolder(@NonNull View itemView) { //ใช้ตัวแปรเกบค่าไว้เ จะได้ไม่เปลืองfindViewById บ่อยๆ
            super(itemView);

            //findView ตรงนี้
            this.placeImageView = itemView.findViewById(R.id.logo_image_view);
            this.nameTextView = itemView.findViewById(R.id.place_name_text_view);
            this.districtTextView = itemView.findViewById(R.id.district_text_view);


            //Intent  จริงๆไม่ควรใส่ตรงนี้ ขึ้นอยู่กลับดีไซต์โค้ดมากกว่า ควรไปอยู่ที่ mainActivity จะดีกว่า
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(mContext, PlaceDetailsActivity.class);
                    intent.putExtra("name",place.name);
                    intent.putExtra("image",place.imageRes);
                    mContext.startActivity(intent);

                }
            });
        }
    }
}
