package android.lab.nakhonpathom.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.lab.nakhonpathom.R;

import androidx.annotation.Nullable;

public class DatabaseHepler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="db";
    private static final int DATABASE_VERSION=1;

    public static final String TABLE_PLACE="place";

    public static final String COL_ID="_id";
    public static final String COL_NAME="name";
    public static final String COL_DISTRICT="district";
    public static final String COL_IMAGE="image";

    private static final String SQL_CREATE_PLACE =
            "CREATE TABLE " + TABLE_PLACE +" ("
                +COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL_NAME+" TEXT, "
                +COL_DISTRICT+" TEXT, "
                +COL_IMAGE+" INTEGER)";

    public DatabaseHepler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //ทำงานเมื่อใช้งาน class DatabaseHepler
        db.execSQL(SQL_CREATE_PLACE);

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME,"พระปฐมเจดีย์");
        cv.put(COL_DISTRICT,"เมือง");
        cv.put(COL_IMAGE, R.drawable.pajade);
        db.insert(TABLE_PLACE,null,cv); // 1 row

        cv.put(COL_NAME,"บ้านปายนา");
        cv.put(COL_DISTRICT,"นครชัยศรี");
        cv.put(COL_IMAGE, R.drawable.banpryna);
        db.insert(TABLE_PLACE,null,cv); // 2 row

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
