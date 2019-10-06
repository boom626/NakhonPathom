package android.lab.nakhonpathom.model;

public class Place {  //เกบพิกัด เกบรูปได้ด้วย

    public int id;
    public String name;
    public String district;
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
