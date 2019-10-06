package android.lab.nakhonpathom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.lab.nakhonpathom.DB.DatabaseHepler;
import android.lab.nakhonpathom.adepter.PlaceListAdepter;
import android.lab.nakhonpathom.adepter.RecyclerViewAdapter;
import android.lab.nakhonpathom.model.Place;
import android.lab.nakhonpathom.room_db.AppDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.lab.nakhonpathom.DB.DatabaseHepler.COL_DISTRICT;
import static android.lab.nakhonpathom.DB.DatabaseHepler.COL_ID;
import static android.lab.nakhonpathom.DB.DatabaseHepler.COL_IMAGE;
import static android.lab.nakhonpathom.DB.DatabaseHepler.COL_NAME;
import static android.lab.nakhonpathom.DB.DatabaseHepler.TABLE_PLACE;

public class MainActivity extends AppCompatActivity {

//    private String[] mPlacelist=new String[]{//obj in class
//            "พระปฐมเจดีย์", "บ้านปายนา", "พิพิธภัณฑ์รถเก่า","ตลาดท่านา","วัดกลาวบางแก้ว","ตลาดน้ำลำพญา","ตลาดน้ำทุ่งบัวแดง","Tree & Tide Riverside"
//    } ;

    //private Place[] mPlacelist= new  Place[10];  //Array ธรรมดา ต้องระบุขนาดสถานที่
     private List<Place> mPlacelist = new ArrayList<>();  // ไม่ต้องระบุขนาดสถานที่

    private RecyclerViewAdapter mAdapter;

//    private DatabaseHelper mDbHelper;
//    private SQLiteDatabase mDatabase;

    private AppDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------room--------------------
        mDatabase=AppDatabase.getInstance(MainActivity.this);
        //mPlacelist=mDatabase.placeDao().getAllPlace(); //ดึงข้อมูล มันทำให้หมด เข้าถึงDB

            //------------New Thread
        //readFromDB();
                //---------
        final RecyclerView recyclerView=findViewById(R.id.recycler_view);

        GetPlaceTask task = new GetPlaceTask(MainActivity.this,mDatabase,recyclerView);
        task.execute();
        //-------------------------------------------------------

        //สร้างเมธอดข้อมูลใส่ไปในที่ mPlacelist
        //populateData();

        //------------------SQLite--------------------------------
//        mDbHelper =new DatabaseHelper(MainActivity.this);
//        mDatabase=mDbHelper.getWritableDatabase();  //อ่านและเขียนได้
//
//        readFormDb();
            //---------------------SQLite : ไปทำเปน เมทอด
//        Cursor cursor=mDatabase.query(TABLE_PLACE,null,null,null ,null,null,null); //queryจากtableชื่อplace nullแรก=select*from=all column   nullสอง =where
//        //mDatabase.query(TABLE_PLACE,null,"district=?",new String[]{"เมือง"},......); //where = เมือง
//        //mDatabase.query(TABLE_PLACE,new String[]{"name","district"},.....);  //เฉพาะบางฟิล
//
//        while (cursor.moveToNext()){//read 1 รอบ ได้ที่ละ row ขึ้นอยู่กับเงื่อนไขที่ระบุในquery
//            //int id =cursor.getInt(cursor.getColumnIndex("_id"));
//            int id =cursor.getInt(cursor.getColumnIndex(COL_ID));
//            String name =cursor.getString(cursor.getColumnIndex(COL_NAME));
//            String district =cursor.getString(cursor.getColumnIndex(COL_DISTRICT));
//            int image =cursor.getInt(cursor.getColumnIndex(COL_IMAGE));
//
//            Place place = new Place(id,name,district,image); //อ่านข้อมูลจากตัวแปล4ตัว/ฟิล เกบลงโมเดล
//            mPlacelist.add(place); //ยัด modelลงรภไฟ แต่ละโบกี้ก็คือแต่ละฟิล

        //--------------------------DB Adapter :SQLite , Room
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        mAdapter = new RecyclerViewAdapter(
//                MainActivity.this,
//                R.layout.item_place,
//                mPlacelist
//        );
//
//        LinearLayoutManager lm
//                = new LinearLayoutManager(MainActivity.this);
//
//        recyclerView.setLayoutManager(lm);
//        recyclerView.setAdapter(mAdapter);


        //------------รอไว้ เมื่อuser กดปุ่ม ถึงทำ ไม่ได้runเลย------------
            Button addPlaceButton =findViewById(R.id.add_place_button);
            addPlaceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //todo: อ่านข้อมูลจาก place_name_edit_text แล้ว insert ลง DB

                    //อ่านข้อมูลจาก place_name_edit_text มาเก็บลงตัวแปร name
                    EditText placeNameEditText = findViewById(R.id.place_name_edit_text);
                    String name =placeNameEditText.getText().toString();

                    //เอาตัวแปร name ไป insert ลง DB : SQLite
//                    ContentValues cv = new ContentValues();
//                    cv.put(COL_NAME,name);
//                    cv.put(COL_DISTRICT,"");//ไม่ได้มีให้กรอก มั่วๆไปก่อน
//                    cv.put(COL_IMAGE, R.mipmap.ic_launcher);//ไม่ได้มีให้กรอก มั่วๆไปก่อน
//                    mDatabase.insert(TABLE_PLACE,null,cv);
//
//                      //------------------------------DB : SQLite
//                    readFormDb();
//                    mAdapter.notifyDataSetChanged();

                    //------------Room
                    final Place place = new Place(0, name ,"",R.mipmap.ic_launcher);
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mDatabase.placeDao().insertPlace(place);
                            //..AsyncTask..ไม่ได้สอนต่อ
                                    //or
//                            mPlacelist= mDatabase.placeDao().getAllPlace();
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mAdapter.notifyDataSetChanged();
//                                }
//                            });
//                                  //or
                            //readFromDB();


                        }
                    });
                    t.start();
                }
            });

        //------------------------------------------------------Adapter---------------------------------------------------------------
//        RecyclerView recyclerView =findViewById(R.id.recycler_view);
//        RecyclerViewAdapter adapter =new RecyclerViewAdapter(
//                MainActivity.this,
//                R.layout.item_place,
//                mPlacelist
//        );
//        //=แนวตั้ง  ต่างกับ list แค่กำหนดแนวตั้งแนวนอนได้
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//        //=แนวนอน ต่างกับ list แค่กำหนดแนวตั้งแนวนอนได้
////        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
////        recyclerView.setLayoutManager(lm);
//
//
//        recyclerView.setAdapter(adapter);
        //----------------------------------------------------------------------------------------------------------------------

        //อ้างอิงListViewไป layout file
        //ListView placeListView =findViewById(R.id.place_list_view);

        //Adapter  แบบ android มี list ใช้array string
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                //parameterส่งให้มันเพื่อจะสร้างobj เปนตัวเริ่มต้น
//                MainActivity.this,//เลเอ้า คือเป็นของแต่ละไอเทมในลิสที่ออกมาแสดง อยากให้มีหน้าตายังไง
//                android.R.layout.simple_list_item_1,  //android มี list ให้   อยากรู้ก็กด ctrl + กด ดู
//           น     mPlacelist//แหล่งข้อมูล ผูกกับadapter เปนตัวกลางเชื่อมไปListView
//        );

        // Adapter  ที่สร้างเอง  ใช้array string ด้วย
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                //parameterส่งให้มันเพื่อจะสร้างobj เปนตัวเริ่มต้น
//                MainActivity.this,//เลเอ้า คือเป็นของแต่ละไอเทมในลิสที่ออกมาแสดง อยากให้มีหน้าตายังไง
//                 R.layout.item_place,  //ที่สร้างเอง ระบุlayout สำหรับแต่ละitem ในlist
//                 R.id.place_name_text_view,  //เป็นการบอกว่าเอาข้อมูลไปใส่ที่นี้นะ  วิวแต่ละitem
//                 mPlacelist//แหล่งข้อมูล ผูกกับadapter เปนตัวกลางเชื่อมไปListView
//        );

        //Adapter ระบุได้ชิ้นเดวเท่านั้น ถ้าจะทำหลายอันทำไง? ใช้array string แบบเดิมไม่ได้แล้ว ต้องทำเปน model   เท่ากับอันแรกคือเปลี่ยนเมธอดgetView
//        PlaceListAdepter adapter = new PlaceListAdepter(
//                //parameterส่งให้มันเพื่อจะสร้างobj เปนตัวเริ่มต้น
//                MainActivity.this,//เลเอ้า คือเป็นของแต่ละไอเทมในลิสที่ออกมาแสดง อยากให้มีหน้าตายังไง
//                R.layout.item_place,  //ที่สร้างเอง ระบุlayout สำหรับแต่ละitem ในlist
//                mPlacelist//แหล่งข้อมูล ผูกกับadapter เปนตัวกลางเชื่อมไปListView
//        );
//
//
//        placeListView.setAdapter(adapter);//เอาAdapter เชื่อม ListView โดยใช้เมธอด setAdapter

        //สร้างListenerเพื่อระบุโค้ดการทำงาน เมื่อแต่ละไอเทมถูกกด  ข้อมูลเปนarray string
//        placeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //new O
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
//                //เมื่อถูกกดจะวิ่งมาที่นี่หมดเลย มีตัวหนึ่งในพารามิเตอร์บอกว่ากดแล้วแสดงตัวใหนคือ position
//                String placeName =mPlacelist[position]; //พัก obj  mPlacelist ไว้ในนี้
//
//                Toast.makeText(MainActivity.this,placeName,Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        placeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //new O
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
//                //เมื่อถูกกดจะวิ่งมาที่นี่หมดเลย มีตัวหนึ่งในพารามิเตอร์บอกว่ากดแล้วแสดงตัวใหนคือ position
//                Place place =mPlacelist.get(position); //พัก obj  mPlacelist ไว้ในนี้
//                String placeName =place.name;
//
//                Toast.makeText(MainActivity.this,placeName,Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(MainActivity.this, PlaceDetailsActivity.class);
//                intent.putExtra("name", place.name);       //input string in putExtra
//                intent.putExtra("image", place.imageRes);  //input int in putExtra
//                startActivity(intent);
//
//            }
//        });
    }


//--------------------------------------------------------------
//    private void readFromDB() {  //Room
//            // สร้าง Woeker Thread
//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                    //this_code in this_method run on Worker Thread
//                mPlacelist=mDatabase.placeDao().getAllPlace();//ไว้นี่แทน จะได้ไม่ไปทำงานกับ Thread หลัก   หลักๆทำบรรทัดนี้อย่างเด่วที่เหลือผูกแสดงผลกับRecyclerViewเพื่อไม่ให้ทำงานก่อนโดยไม่รอ
//
//                final RecyclerView recyclerView = findViewById(R.id.recycler_view);
//                mAdapter = new RecyclerViewAdapter(
//                        MainActivity.this,
//                        R.layout.item_place,
//                        mPlacelist
//                );
//
//                final LinearLayoutManager lm
//                        = new LinearLayoutManager(MainActivity.this);
//
//                     //bg Thread not run UI :กฏ android ต้องกลับไปrunที่Threadหลัก   =ย้ายโค้ดที่เป็นการเข้าถึง UI ส่งกลับไปrun UI Thread /Main Thread
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                          //this_code in this_method run on UI Thread /Main Thread
//                        recyclerView.setLayoutManager(lm);
//                        recyclerView.setAdapter(mAdapter);
//                    }
//                });
//            }
//        });
//        t.start();
//    }

    //-----------------------------------------------------------------------------------
   // private void readFormDb() {  //: SQLite
//        mPlacelist.clear();
//
//        Cursor cursor = mDatabase.query(TABLE_PLACE, null, null, null, null, null, null);
//
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
//            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
//            String district = cursor.getString(cursor.getColumnIndex(COL_DISTRICT));
//            int image = cursor.getInt(cursor.getColumnIndex(COL_IMAGE));
//
//            Place place = new Place(id, name, district, image);
//            mPlacelist.add(place);
//        }
    //}

    //---------------------------------------------------------------------------------------------------------------------------------------------
    private void populateData() { //เปนการแยกส่วนโค้ด ส่วนข้อมุลเข้าไป mPlacelist จะได้ไม่กระจุกกองไว้ที่เดียว  //เก็บData
       //แต่ละอันค่อยๆใส่
//        Place place =new Place();
//        place.name ="พระปฐมเจดียื";
//        place.district ="เมือง";

        //หรือไปสร้างคอนตัก ในmodel
        Place place =new Place("พระปฐมเจดียื","เมือง",R.drawable.pajade);
        mPlacelist.add(place);

        place =new Place("บ้านปายนา","นครชัยศรี",R.drawable.banpryna);
        mPlacelist.add(place);

        place =new Place("พิพิธภัณฑ์รถเก่า","นครชัยศรี",R.drawable.carold);
        mPlacelist.add(place);

        place =new Place("ตลาดท่านา","นครชัยศรี",R.drawable.thanamarket);
        mPlacelist.add(place);

        place =new Place("วัดกลาวบางแก้ว","นครชัยศรี",R.drawable.wadganbangall);
        mPlacelist.add(place);

        place =new Place("ตลาดน้ำลำพญา","บางเลน",R.drawable.lamphayamarket9);
        mPlacelist.add(place);

        place =new Place("ตลาดน้ำทุ่งบัวแดง","บางเลน",R.drawable.lotasrad);
        mPlacelist.add(place);

        place =new Place("Tree & Tide Riverside","บางเลน",R.drawable.tree);
        mPlacelist.add(place);
    }
    //------------------------------room--------------------------------
    private static class GetPlaceTask extends AsyncTask<Void,Void,List<Place>>{
        private Context mContext;
        private AppDatabase mDatabase;
        private RecyclerView mRecyclerView;

        public GetPlaceTask(Context mContext,AppDatabase db,RecyclerView r) {//พอ new class ส่งcontext เข้ามาพักที่ฟิล mContext
            this.mContext = mContext;
            this.mDatabase=db;
            this.mRecyclerView=r;
        }

        @Override
        protected List<Place> doInBackground(Void... voids) {  //ทำ bg Thread
            return mDatabase.placeDao().getAllPlace();//อ่านข้อมูลทั้งหมด ส่งไป
        }

        @Override
        protected void onPostExecute(List<Place> placeList) { //หลังทำ doInBackgroundเสร็จ จะreturnข้อมูลส่งมาเป็นพารามิเตอร์ให้ onPostExecute  เพื่อเชื่อมโค้ดกัน จะผลักมา UI /Main Thread
            super.onPostExecute(placeList);

            final RecyclerViewAdapter adapter = new RecyclerViewAdapter(
                    mContext,
                    R.layout.item_place,
                    placeList
            );

            final LinearLayoutManager lm
                    = new LinearLayoutManager(mContext);

            //bg Thread not run UI :กฏ android ต้องกลับไปrunที่Threadหลัก
                    mRecyclerView.setLayoutManager(lm);
                    mRecyclerView.setAdapter(adapter);

        }
    }
}//ปิด MainActivity
