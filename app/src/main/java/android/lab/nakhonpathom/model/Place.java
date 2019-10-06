package android.lab.nakhonpathom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "place")
public class Place {  //เกบพิกัด เกบรูปได้ด้วย   =model

    @PrimaryKey(autoGenerate = true) //จะอยู่บนประกาศตัวแปล   ,autoGenerate เพื่อเวลาเพิ่มข้อมูลไม่ต้องทำอะไรกับฟิลนี้ มันจะทำให้เอง
    public int id;

    @ColumnInfo(name = "name")  //ตัวแปร name เวลาเกบในDB จะใช้ชื่ออะไร
    public String name;

    @ColumnInfo(name = "district")
    public String district;

    @ColumnInfo(name = "image")
    public int imageRes;

    //code - gene....
    public Place(String name,String district, int imageRes) {
        this.name = name;
        this.district = district;
        this.imageRes = imageRes;
    }

    //DB
    public Place(int id,String name,String district, int imageRes) {
        this.id=id;
        this.name = name;
        this.district = district;
        this.imageRes = imageRes;
    }
}
