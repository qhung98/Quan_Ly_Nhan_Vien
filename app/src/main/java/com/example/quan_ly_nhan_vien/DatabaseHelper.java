package com.example.quan_ly_nhan_vien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    private static final String DB_NAME = "QL_NHAN_VIEN";
    private static final String TABLE_NAME = "Nhan_Vien";

    private static final String ID = "id";
    private static final String TEN = "ten";
    private static final String GIOI_TINH = "gioiTinh";
    private static final String NAM_SINH = "namSinh";
    private static final String QUE_QUAN = "queQuan";
    private static final String HOC_VAN = "hocVan";
    private static final String CHUYEN_MON = "chuyenMon";
    private static final String DAO_TAO = "daoTao";
    private static final String LAM_VIEC = "lamViec";
    private static final String PHONG = "phong";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TEN + " TEXT, " +
                GIOI_TINH + " TEXT, " +
                NAM_SINH + " INTEGER, " +
                QUE_QUAN + " TEXT, " +
                HOC_VAN + " TEXT, " +
                CHUYEN_MON + " TEXT, " +
                DAO_TAO + " TEXT, " +
                LAM_VIEC + " INTEGER, " +
                PHONG + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void themNhanVien(NhanVien nhanVien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TEN, nhanVien.getTen());
        contentValues.put(GIOI_TINH, nhanVien.getGioiTinh());
        contentValues.put(NAM_SINH, nhanVien.getNamSinh());
        contentValues.put(QUE_QUAN, nhanVien.getQueQuan());
        contentValues.put(HOC_VAN, nhanVien.getHocVan());
        contentValues.put(CHUYEN_MON, nhanVien.getChuyenMon());
        contentValues.put(DAO_TAO, nhanVien.getDaoTao());
        contentValues.put(LAM_VIEC, nhanVien.getLamViec());
        contentValues.put(PHONG, nhanVien.getPhong());

        db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<NhanVien> getAllNhanVien(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getInt(8), cursor.getString(9));

            list.add(nhanVien);
        }

        cursor.close();
        db.close();

        return list;
    }

    public NhanVien getNhanVienById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID + "=?",  new String[]{Integer.toString(id)});

        if (cursor.getCount()>0)
            cursor.moveToFirst();

        NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7),
                cursor.getInt(8), cursor.getString(9));

        cursor.close();
        db.close();
        return nhanVien;
    }

    public void suaNhanVien(NhanVien nhanVien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TEN, nhanVien.getTen());
        contentValues.put(GIOI_TINH, nhanVien.getGioiTinh());
        contentValues.put(NAM_SINH, nhanVien.getNamSinh());
        contentValues.put(QUE_QUAN, nhanVien.getQueQuan());
        contentValues.put(HOC_VAN, nhanVien.getHocVan());
        contentValues.put(CHUYEN_MON, nhanVien.getChuyenMon());
        contentValues.put(DAO_TAO, nhanVien.getDaoTao());
        contentValues.put(LAM_VIEC, nhanVien.getLamViec());
        contentValues.put(PHONG, nhanVien.getPhong());

        db.update(TABLE_NAME, contentValues, ID + "=?", new String[]{Integer.toString(nhanVien.getId())});
    }

    public ArrayList<NhanVien> lietKe(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + LAM_VIEC + " > 3 AND " + CHUYEN_MON + "='CNTT'", null);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getInt(8), cursor.getString(9));

            list.add(nhanVien);
        }

        return list;
    }

    public ArrayList<NhanVien> thongKeTuoi(String tuoi){
        String[] arr = tuoi.split("\\-");
        ArrayList<Integer> listTuoi= new ArrayList<>();
        for(String i:arr){
            listTuoi.add(Integer.parseInt(i));
        }

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int a = currentYear - listTuoi.get(0);
        int b = currentYear - listTuoi.get(1);

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAM_SINH + " >=" + b + " AND " + NAM_SINH + " <=" + a, null);

//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAM_SINH + " >= 1980 AND " + NAM_SINH + " <= 2000", null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getInt(8), cursor.getString(9));

            list.add(nhanVien);
        }

        return list;
    }

    public ArrayList<NhanVien> thongKeGioiTinh(String gioiTinh){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +  GIOI_TINH + "=?", new String[]{gioiTinh});

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getInt(8), cursor.getString(9));

            list.add(nhanVien);
        }

        return list;
    }

    public ArrayList<NhanVien> thongKeChuyenMon(String chuyenMon){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CHUYEN_MON + "=?", new String[]{chuyenMon});

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getInt(8), cursor.getString(9));

            list.add(nhanVien);
        }

        return list;
    }
}
